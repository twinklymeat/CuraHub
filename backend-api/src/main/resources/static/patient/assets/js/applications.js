document.addEventListener('DOMContentLoaded', () => {
  const list = document.getElementById('applicationsList');
  const empty = document.getElementById('applicationsEmpty');

  const apps = loadApplications();
  if (!apps.length) {
    if (empty) empty.style.display = 'block';
    return;
  }

  if (empty) empty.style.display = 'none';
  if (!list) return;

  list.innerHTML = apps
    .sort((a, b) => new Date(b.time).getTime() - new Date(a.time).getTime())
    .map(renderCard)
    .join('');

  function renderCard(app) {
    const date = formatDate(app.time);
    const reason = app.reason ? escapeHtml(app.reason) : 'Not specified';
    const contact = app.contact ? escapeHtml(app.contact) : 'Not provided';
    const doctorName = escapeHtml(app.doctorName || `Doctor #${app.doctorId ?? ''}`);
    return `
      <div class="card doctor-card">
        <div class="meta">
          <h3>${doctorName}</h3>
          <div class="tags">
            <span class="tag">Sent: ${date}</span>
            ${app.doctorEmail ? `<span class="tag">${escapeHtml(app.doctorEmail)}</span>` : ''}
          </div>
          <small class="muted">Reason: ${reason}</small><br>
          <small class="muted">Preferred contact: ${contact}</small>
        </div>
      </div>
    `;
  }

  function loadApplications() {
    try {
      const data = JSON.parse(localStorage.getItem('patientApplications') || '[]');
      return Array.isArray(data) ? data : [];
    } catch (err) {
      console.error('Failed to load applications', err);
      return [];
    }
  }

  function formatDate(value) {
    const d = new Date(value);
    if (Number.isNaN(d.getTime())) return 'Unknown date';
    return d.toLocaleString(undefined, {
      month: 'short',
      day: 'numeric',
      hour: 'numeric',
      minute: '2-digit'
    });
  }

  function escapeHtml(str) {
    return String(str)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&#039;');
  }
});

