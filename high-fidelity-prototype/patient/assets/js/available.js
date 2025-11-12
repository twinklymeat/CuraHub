document.addEventListener('DOMContentLoaded', () => {
  const list = document.getElementById('doctorList');
  if (!list) return;

  function doctorCardTemplate(doctor) {
    const doctorId = doctor.id ?? doctor.ID ?? 'N/A';
    const user = doctor.user ?? {};
    const fullName = [user.firstName, user.lastName].filter(Boolean).join(' ') || `Doctor #${doctorId}`;
    const description = doctor.description || 'Provider';
    const email = user.email || '';
    const phone = user.phone || '';
    const avatarSeed = Number.isFinite(Number(doctorId)) ? (Number(doctorId) % 70) + 1 : 1;

    return `
      <div class="card">
        <div class="doctor-card">
          <img class="avatar" alt="${fullName}" src="https://i.pravatar.cc/112?img=${avatarSeed}">
          <div class="meta">
            <h3>${fullName}</h3>
            <div class="tags">
              <span class="tag">${description}</span>
              ${email ? `<span class="tag">${email}</span>` : ''}
            </div>
            ${phone ? `<small>${phone}</small>` : ''}
          </div>
          <div class="actions-right">
            <button class="btn primary" onclick="applyForCare('${fullName.replace(/'/g, "\\'")}')">Apply for Care</button>
            <button class="btn secondary check-availability" data-doctor-id="${doctorId}">Check Availability</button>
          </div>
        </div>
        <div class="availability-result muted" data-for-doctor="${doctorId}" style="margin-top:8px;font-size:0.9rem"></div>
      </div>
    `;
  }

  function renderDoctors(doctors) {
    if (!Array.isArray(doctors) || doctors.length === 0) {
      list.innerHTML = '<div class="muted">No doctors available right now.</div>';
      return;
    }
    list.innerHTML = doctors.map(doctorCardTemplate).join('');
  }

  async function loadDoctors() {
    try {
      const doctors = await fetchJSON('/doctors');
      renderDoctors(doctors);
      attachAvailabilityHandlers();
    } catch (error) {
      console.error('Failed to load doctors', error);
      list.innerHTML = `<div class="muted">Unable to load doctors (HTTP ${error.status ?? 'Error'}).</div>`;
    }
  }

  function attachAvailabilityHandlers() {
    const buttons = list.querySelectorAll('.check-availability');
    buttons.forEach((btn) => {
      btn.addEventListener('click', async () => {
        const doctorId = btn.dataset.doctorId;
        if (!doctorId) return;
        const resultContainer = list.querySelector(`.availability-result[data-for-doctor="${doctorId}"]`);
        if (!resultContainer) return;
        btn.disabled = true;
        const previousText = btn.textContent;
        btn.textContent = 'Loading...';
        try {
          const availability = await fetchJSON(`/availability/doctor/${doctorId}`);
          renderAvailability(resultContainer, availability);
        } catch (error) {
          console.error('Failed to load availability', error);
          resultContainer.innerHTML = `<div class="muted">Unable to load availability (HTTP ${error.status ?? 'Error'}).</div>`;
        } finally {
          btn.disabled = false;
          btn.textContent = previousText;
        }
      });
    });
  }

  function renderAvailability(container, availability) {
    if (!Array.isArray(availability) || availability.length === 0) {
      container.innerHTML = '<div class="muted">No upcoming availability.</div>';
      container.classList.remove('muted');
      return;
    }
    const items = availability.map((slot) => {
      const start = formatDateTime(slot.startTime);
      const end = formatDateTime(slot.endTime);
      const length = formatLength(slot.length);
      return `
        <li style="margin-bottom:6px">
          <div><strong>${start}</strong> → ${end}</div>
          <small class="muted">Duration: ${length}</small>
        </li>`;
    }).join('');
    container.innerHTML = `
      <div class="panel" style="margin-top:6px;padding:12px;background:#ffffff;color:#0f172a;border:1px solid #d1d5db;border-radius:10px">
        <strong>Upcoming slots</strong>
        <ul style="margin:8px 0 0 18px; padding:0 0 0 4px; list-style:disc">
          ${items}
        </ul>
      </div>`;
    container.classList.remove('muted');
  }

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

  function formatLength(length) {
    if (!length) return 'N/A';
    if (typeof length === 'string') {
      const parts = length.split(':').map(Number);
      if (parts.length >= 2) {
        const [hours = 0, minutes = 0, seconds = 0] = parts;
        return [
          hours ? `${hours}h` : '',
          minutes ? `${minutes}m` : '',
          seconds ? `${seconds}s` : ''
        ].filter(Boolean).join(' ') || 'N/A';
      }
      return length;
    }
    if (typeof length === 'object' && length !== null) {
      const { hours = 0, minutes = 0, seconds = 0 } = length;
      return [
        hours ? `${hours}h` : '',
        minutes ? `${minutes}m` : '',
        seconds ? `${seconds}s` : ''
      ].filter(Boolean).join(' ') || 'N/A';
    }
    return String(length);
  }

  loadDoctors();
});


