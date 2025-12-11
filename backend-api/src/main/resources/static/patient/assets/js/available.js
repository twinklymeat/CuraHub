document.addEventListener('DOMContentLoaded', () => {
  const list = document.getElementById('doctorList');
  if (!list) return;

  const doctorsById = new Map();
  const modal = document.getElementById('applyModal');
  const modalDoctorLabel = document.getElementById('applyDoctorLabel');
  const modalForm = document.getElementById('applyForm');
  const modalReason = document.getElementById('applyReason');
  const modalContact = document.getElementById('applyContact');
  const modalError = document.getElementById('applyError');
  const modalSubmit = document.getElementById('applySubmit');
  const modalCancel = document.getElementById('applyCancel');
  const modalClose = document.getElementById('applyClose');
  let selectedDoctorId = null;

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
            <button class="btn primary apply-care" data-doctor-id="${doctorId}">Apply for Care</button>
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
    doctorsById.clear();
    doctors.forEach((dr) => {
      const id = dr.id ?? dr.ID;
      if (id !== undefined && id !== null) {
        doctorsById.set(String(id), dr);
      }
    });
    list.innerHTML = doctors.map(doctorCardTemplate).join('');
  }

  async function loadDoctors() {
    try {
      const doctors = await fetchJSON('/doctors');
      renderDoctors(doctors);
      attachAvailabilityHandlers();
      attachApplyHandlers();
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

  function attachApplyHandlers() {
    const buttons = list.querySelectorAll('.apply-care');
    buttons.forEach((btn) => {
      btn.addEventListener('click', async () => {
        const doctorId = btn.dataset.doctorId;
        openApplyModal(doctorId);
      });
    });
  }

  function openApplyModal(doctorId) {
    const doctor = doctorsById.get(String(doctorId));
    if (!doctor) {
      alert('Unable to find this doctor. Please refresh and try again.');
      return;
    }
    selectedDoctorId = String(doctorId);
    const doctorUser = doctor.user ?? {};
    const doctorName = [doctorUser.firstName, doctorUser.lastName].filter(Boolean).join(' ') || `Doctor #${doctorId}`;
    if (modalDoctorLabel) modalDoctorLabel.textContent = `Sending request to ${doctorName}`;
    if (modalReason) modalReason.value = '';
    if (modalContact) modalContact.value = '';
    if (modalError) modalError.textContent = '';
    if (modal) {
      modal.style.display = 'flex';
      setTimeout(() => modalReason?.focus(), 50);
    }
  }

  function closeApplyModal() {
    selectedDoctorId = null;
    setModalBusy(false);
    if (modal) modal.style.display = 'none';
  }

  modalCancel?.addEventListener('click', closeApplyModal);
  modalClose?.addEventListener('click', closeApplyModal);

  modalForm?.addEventListener('submit', async (event) => {
    event.preventDefault();
    if (!selectedDoctorId) {
      alert('Please select a doctor again.');
      closeApplyModal();
      return;
    }
    const reason = (modalReason?.value || '').trim();
    const contact = (modalContact?.value || '').trim();
    if (!reason) {
      if (modalError) modalError.textContent = 'Please enter a reason for your visit.';
      modalReason?.focus();
      return;
    }
    if (modalError) modalError.textContent = '';
    setModalBusy(true);
    await handleApplyForCare(selectedDoctorId, { reason, contact });
    setModalBusy(false);
    closeApplyModal();
  });

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

  async function handleApplyForCare(doctorId, details = {}) {
    const doctor = doctorsById.get(String(doctorId));
    if (!doctor) {
      alert('Unable to find this doctor. Please refresh and try again.');
      return;
    }

    const patient = getCachedPatientInfo();
    if (!patient.id || !patient.email) {
      alert('Please log in first so we can attach your account to the request.');
      return;
    }

    const doctorUser = doctor.user ?? {};
    const doctorName = [doctorUser.firstName, doctorUser.lastName].filter(Boolean).join(' ') || `Doctor #${doctorId}`;
    const doctorEmail = doctorUser.email;

    if (!doctorEmail) {
      alert('This doctor does not have an email on file yet. Please contact support.');
      return;
    }

    const patientName = patient.name || 'Patient';
    const reason = (details.reason || '').trim();
    const contact = (details.contact || '').trim();
    const subjectForDoctor = `New appointment request from ${patientName}`;
    const subjectForPatient = `Appointment request sent to ${doctorName}`;

    const bodyForDoctor = [
      `You have a new appointment request from ${patientName}.`,
      `Patient email: ${patient.email} ID ${patient.id}`,
      reason ? `Reason: ${reason}` : null,
      contact ? `Preferred contact: ${contact}` : null,
      `Requested doctor: ${doctorName} (ID ${doctorId})`,
      '',
      'Please follow up in the dashboard.'
    ].filter(Boolean).join('\n');

    const bodyForPatient = [
      `Hi ${patientName || 'there'},`,
      `We received your request with ${doctorName}.`,
      reason ? `Reason you provided: ${reason}` : null,
      'We will notify you when it is confirmed.',
      '',
      '— CuraHub'
    ].filter(Boolean).join('\n');

    try {
      btnSetBusy(doctorId, true);
      await Promise.all([
        sendEmail({ to: doctorEmail, subject: subjectForDoctor, text: bodyForDoctor }),
        sendEmail({ to: patient.email, subject: subjectForPatient, text: bodyForPatient })
      ]);
      recordApplication({
        doctorId,
        doctorName,
        doctorEmail,
        patientEmail: patient.email,
        reason,
        contact
      });
      alert('Request sent! We emailed the doctor and a receipt to you.');
    } catch (error) {
      console.error('Failed to send emails', error);
      alert(`Could not send email (HTTP ${error.status ?? 'Error'}). Please try again.`);
    } finally {
      btnSetBusy(doctorId, false);
    }
  }

  function btnSetBusy(doctorId, busy) {
    const btn = list.querySelector(`.apply-care[data-doctor-id="${doctorId}"]`);
    if (!btn) return;
    btn.disabled = busy;
    btn.textContent = busy ? 'Sending…' : 'Apply for Care';
  }

  function setModalBusy(busy) {
    if (modalSubmit) {
      modalSubmit.disabled = busy;
      modalSubmit.textContent = busy ? 'Sending…' : 'Send request';
    }
    if (modalCancel) modalCancel.disabled = busy;
  }

  function recordApplication(entry) {
    try {
      const existing = JSON.parse(localStorage.getItem('patientApplications') || '[]');
      const now = new Date().toISOString();
      existing.push({
        ...entry,
        time: now
      });
      localStorage.setItem('patientApplications', JSON.stringify(existing));
    } catch (err) {
      console.error('Failed to cache application', err);
    }
  }

  // Expose for any legacy inline handlers.
  window.applyForCare = (doctorId) => openApplyModal(doctorId);

  loadDoctors();
});


