document.addEventListener('DOMContentLoaded', () => {
  const list = document.getElementById('apptsList');
  const empty = document.getElementById('apptsEmpty');
  const patientId = localStorage.getItem('patientUserId');
  const patientEmail = localStorage.getItem('patientUserEmail');
  const patientName = localStorage.getItem('patientUserName');

  if (!patientId) {
    if (empty) {
      empty.style.display = 'block';
      empty.textContent = 'Please log in to view your appointments.';
    }
    return;
  }

  loadAppointments();

  async function loadAppointments() {
    try {
      const data = await fetchJSON(`/appointments/user/${patientId}`);
      renderAppointments(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error('Failed to load appointments', error);
      if (empty) {
        empty.style.display = 'block';
        empty.textContent = `Unable to load appointments (HTTP ${error.status ?? 'Error'}).`;
      }
    }
  }

  function renderAppointments(appts) {
    if (!appts.length) {
      if (empty) empty.style.display = 'block';
      return;
    }
    if (empty) empty.style.display = 'none';
    if (!list) return;

    list.innerHTML = appts
      .sort((a, b) => new Date(b.time).getTime() - new Date(a.time).getTime())
      .map(renderCard)
      .join('');
  }

  function renderCard(appt) {
    const time = formatDateTime(appt.time);
    const isPast = isPastDate(appt.time);
    const doctor = appt.doctor || {};
    const doctorUser = doctor.user || {};
    const doctorName = [doctorUser.firstName, doctorUser.lastName].filter(Boolean).join(' ') || `Doctor #${doctor.id ?? doctor.ID ?? ''}`;
    const doctorEmail = doctorUser.email || '';
    const notes = appt.notes || 'No notes provided';

    const reviewSection = isPast ? reviewForm(appt, doctorName, doctorEmail) : `<div class="muted" style="margin-top:8px;">Reviews are available after your appointment time.</div>`;

    return `
      <div class="card doctor-card">
        <div class="meta">
          <h3>${escapeHtml(doctorName)}</h3>
          <div class="tags">
            <span class="tag">${escapeHtml(time)}</span>
            ${doctorEmail ? `<span class="tag">${escapeHtml(doctorEmail)}</span>` : ''}
          </div>
          <small class="muted">Notes: ${escapeHtml(notes)}</small>
          ${reviewSection}
        </div>
      </div>
    `;
  }

  function reviewForm(appt, doctorName, doctorEmail) {
    const apptId = appt.id ?? appt.ID;
    const doctorId = (appt.doctor && (appt.doctor.id ?? appt.doctor.ID)) ?? '';
    const formId = `review-form-${apptId}`;
    return `
      <form class="review-form" id="${formId}" style="margin-top:10px;">
        <label style="font-weight:600;">Leave a review</label>
        <div style="display:flex; gap:10px; align-items:center; flex-wrap:wrap;">
          <select name="rating" required>
            <option value="">Rating</option>
            <option value="5">5</option>
            <option value="4">4</option>
            <option value="3">3</option>
            <option value="2">2</option>
            <option value="1">1</option>
          </select>
          <input type="text" name="content" placeholder="Your feedback" required style="flex:1; min-width: 200px;">
        </div>
        <input type="hidden" name="doctorId" value="${doctorId}">
        <div class="actions" style="margin-top:8px; gap:8px; justify-content:flex-start;">
          <button type="submit" class="btn primary" data-appt="${apptId}">Submit review</button>
          <span class="muted">For ${escapeHtml(doctorName)}</span>
        </div>
        <div class="muted" id="msg-${apptId}" style="margin-top:4px; min-height: 18px;"></div>
      </form>
    `;
  }

  list?.addEventListener('submit', async (event) => {
    const form = event.target;
    if (!(form instanceof HTMLFormElement)) return;
    if (!form.classList.contains('review-form')) return;
    event.preventDefault();

    const btn = form.querySelector('button[type="submit"]');
    const msg = form.querySelector(`#msg-${btn?.dataset.appt || ''}`);
    const rating = form.elements.rating?.value;
    const content = form.elements.content?.value?.trim();
    const doctorId = form.elements.doctorId?.value;

    if (!rating || !content || !doctorId) {
      if (msg) msg.textContent = 'Please fill rating and feedback.';
      return;
    }

    setBusy(btn, true);
    if (msg) msg.textContent = '';
    try {
      const payload = {
        doctor: { id: Number(doctorId) },
        user: { id: Number(patientId) },
        rating: Number(rating),
        content
      };
      await fetchJSON('/reviews', {
        method: 'POST',
        body: JSON.stringify(payload)
      });
      if (msg) {
        msg.textContent = 'Review submitted!';
        msg.style.color = '#22c55e';
      }
      form.reset();
    } catch (error) {
      console.error('Failed to submit review', error);
      if (msg) {
        msg.textContent = `Failed to submit (HTTP ${error.status ?? 'Error'}).`;
        msg.style.color = '#ef4444';
      }
    } finally {
      setBusy(btn, false);
    }
  });

  function formatDateTime(value) {
    if (!value) return 'Unknown time';
    const parsed = new Date(value);
    if (Number.isNaN(parsed.getTime())) {
      return value;
    }
    return parsed.toLocaleString(undefined, {
      month: 'short',
      day: 'numeric',
      hour: 'numeric',
      minute: '2-digit'
    });
  }

  function isPastDate(value) {
    const parsed = new Date(value);
    if (Number.isNaN(parsed.getTime())) return false;
    return parsed.getTime() <= Date.now();
  }

  function escapeHtml(str) {
    return String(str)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&#039;');
  }

  function setBusy(btn, busy) {
    if (!btn) return;
    btn.disabled = busy;
    btn.textContent = busy ? 'Submitting…' : 'Submit review';
  }
});

