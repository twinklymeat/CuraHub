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
          </div>
        </div>
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
    } catch (error) {
      console.error('Failed to load doctors', error);
      list.innerHTML = `<div class="muted">Unable to load doctors (HTTP ${error.status ?? 'Error'}).</div>`;
    }
  }

  loadDoctors();
});


