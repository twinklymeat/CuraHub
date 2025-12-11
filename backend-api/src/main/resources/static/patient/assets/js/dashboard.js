document.addEventListener('DOMContentLoaded', () => {
  const grid = document.getElementById('upcomingGrid');
  const empty = document.getElementById('upcomingEmpty');

  if (!grid || !empty) return;

  const cached = typeof getCachedPatientInfo === 'function' ? getCachedPatientInfo() : {};
  const userId = cached?.id;

  if (!userId) {
    empty.style.display = 'block';
    empty.textContent = 'Please log in to view your appointments.';
    return;
  }

  loadUpcoming();

  async function loadUpcoming() {
    showEmpty('Loading your appointments…', true);
    try {
      const data = await fetchJSON(`/appointments/user/${userId}`);
      const appts = Array.isArray(data) ? data : [];
      const confirmed = appts
        .filter((appt) => !isPastDate(appt.time))
        .map((appt) => ({ ...appt, __type: 'confirmed' }));

      const pending = readPendingApplications().map((app) => ({
        __type: 'pending',
        doctor: {
          id: app.doctorId,
          user: {
            firstName: app.doctorName || '',
            lastName: ''
          }
        },
        time: app.time, // when the request was made
        notes: app.reason || '',
        contact: app.contact || '',
        doctorEmail: app.doctorEmail || ''
      }));

      const combined = [...confirmed, ...pending].sort((a, b) => {
        const aPending = a.__type === 'pending';
        const bPending = b.__type === 'pending';
        if (aPending !== bPending) return aPending ? 1 : -1; // confirmed first
        const at = new Date(a.time || '').getTime();
        const bt = new Date(b.time || '').getTime();
        return (at || Infinity) - (bt || Infinity);
      });

      render(combined);
    } catch (error) {
      console.error('Failed to load upcoming appointments', error);
      showEmpty(`Unable to load appointments (HTTP ${error.status ?? 'Error'}).`);
    }
  }

  function render(appts) {
    if (!appts.length) {
      showEmpty('No upcoming appointments yet.');
      grid.innerHTML = '';
      return;
    }
    hideEmpty();
    grid.innerHTML = appts.map(renderCard).join('');
  }

  function renderCard(appt) {
    const isPending = appt.__type === 'pending';
    const doctor = appt.doctor || {};
    const docUser = doctor.user || {};
    const name = isPending
      ? (docUser.firstName || doctor.pendingName || formatDoctorName(docUser, doctor))
      : formatDoctorName(docUser, doctor);
    const specialty = doctor.specialty || '';
    const time = isPending
      ? formatDateTime(appt.time) || 'Pending request'
      : formatDateTime(appt.time);
    const notes = appt.notes || (isPending ? 'Request submitted' : '');
    const contact = appt.contact || '';
    return `
      <div class="card doctor-card">
        <img class="avatar" src="https://i.pravatar.cc/400?img=${(doctor.id ?? doctor.ID ?? 1) % 70}" alt="${escapeHtml(name)}">
        <div class="meta">
          <h3>${escapeHtml(name)}${specialty ? ` — ${escapeHtml(specialty)}` : ''}</h3>
          <div class="tags">
            <span class="tag">${escapeHtml(time || 'Pending request')}</span>
            ${isPending ? '<span class="tag">Pending</span>' : ''}
            ${notes ? `<span class="tag">${escapeHtml(notes)}</span>` : ''}
            ${contact ? `<span class="tag">Contact: ${escapeHtml(contact)}</span>` : ''}
          </div>
        </div>
      </div>
    `;
  }

  function readPendingApplications() {
    try {
      const data = JSON.parse(localStorage.getItem('patientApplications') || '[]');
      return Array.isArray(data) ? data : [];
    } catch (err) {
      console.warn('Unable to read pending applications', err);
      return [];
    }
  }

  function formatDoctorName(docUser, doctor) {
    const full = [docUser.firstName, docUser.lastName].filter(Boolean).join(' ').trim();
    if (full) return `Dr. ${full}`;
    const fallbackId = doctor.id ?? doctor.ID;
    return fallbackId ? `Doctor #${fallbackId}` : 'Your provider';
  }

  function formatDateTime(value) {
    if (!value) return 'Unknown time';
    const parsed = new Date(value);
    if (Number.isNaN(parsed.getTime())) return value;
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

  function showEmpty(message, muted = false) {
    empty.style.display = 'block';
    empty.textContent = message;
    empty.classList.toggle('muted', muted);
  }

  function hideEmpty() {
    empty.style.display = 'none';
  }

  function escapeHtml(str) {
    return String(str ?? '')
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&#039;');
  }
});
