document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('profileForm');
  if (!form) return;

  const fullNameInput = document.getElementById('profileName');
  const emailInput = document.getElementById('profileEmail');
  const phoneInput = document.getElementById('profilePhone');
  const dobInput = document.getElementById('profileDob');
  const pcpInput = document.getElementById('profilePcp');
  const feedback = document.getElementById('profileFeedback');
  const submitBtn = document.getElementById('profileSubmit');

  const cached = typeof getCachedPatientInfo === 'function' ? getCachedPatientInfo() : {};
  const userId = cached?.id;
  const extrasKey = `curahub:patientProfile:${userId || 'anon'}`;

  if (!userId) {
    showFeedback('Please log in again to edit your profile.', true);
    disableForm();
    return;
  }

  loadProfile();

  form.addEventListener('submit', async (event) => {
    event.preventDefault();
    clearFeedback();
    if (!userId) return;

    const fullName = fullNameInput.value.trim();
    const [firstName, ...rest] = fullName.split(/\s+/);
    const lastName = rest.join(' ').trim();

    if (!firstName || !lastName) {
      showFeedback('Please enter both your first and last name.', true);
      return;
    }

    setBusy(true);
    try {
      const payload = {
        id: Number(userId),
        firstName,
        lastName,
        email: emailInput.value.trim(),
        phone: phoneInput.value.trim() || 'N/A'
      };

      await fetchJSON(`/users/${userId}`, {
        method: 'PUT',
        body: JSON.stringify(payload)
      });

      cacheUpdatedUser(payload);
      persistExtras();
      showFeedback('Profile saved.', false);
    } catch (error) {
      console.error('Save profile failed', error);
      showFeedback(`Unable to save profile (HTTP ${error.status ?? 'Error'})`, true);
    } finally {
      setBusy(false);
    }
  });

  async function loadProfile() {
    setBusy(true);
    showFeedback('Loading profile…', false, true);
    try {
      const user = await fetchJSON(`/users/${userId}`);
      if (user) {
        fullNameInput.value = [user.firstName, user.lastName].filter(Boolean).join(' ').trim();
        emailInput.value = user.email ?? '';
        phoneInput.value = user.phone ?? '';
        cacheUpdatedUser(user);
      }
      applyExtras();
      showFeedback('');
    } catch (error) {
      console.error('Fetch profile failed', error);
      showFeedback(`Unable to load profile (HTTP ${error.status ?? 'Error'})`, true);
    } finally {
      setBusy(false);
    }
  }

  function applyExtras() {
    const extras = readExtras();
    if (!extras) return;
    if (dobInput && extras.dob) dobInput.value = extras.dob;
    if (pcpInput && extras.pcp) pcpInput.value = extras.pcp;
  }

  function persistExtras() {
    if (!dobInput && !pcpInput) return;
    const extras = {
      dob: dobInput?.value || '',
      pcp: pcpInput?.value || ''
    };
    localStorage.setItem(extrasKey, JSON.stringify(extras));
  }

  function readExtras() {
    try {
      const raw = localStorage.getItem(extrasKey);
      return raw ? JSON.parse(raw) : null;
    } catch (err) {
      console.warn('Unable to read profile extras', err);
      return null;
    }
  }

  function showFeedback(message, isError = false, keepMuted = false) {
    if (!feedback) return;
    feedback.textContent = message;
    feedback.classList.toggle('muted', keepMuted || !message);
    feedback.style.color = isError ? '#fca5a5' : '';
  }

  function clearFeedback() {
    showFeedback('');
  }

  function setBusy(busy) {
    if (submitBtn) {
      submitBtn.disabled = busy;
      submitBtn.textContent = busy ? 'Saving…' : 'Save Changes';
    }
  }

  function disableForm() {
    form.querySelectorAll('input, button').forEach((el) => {
      el.disabled = true;
    });
  }

  function cacheUpdatedUser(user) {
    if (!user) return;
    if (user.email) localStorage.setItem('patientUserEmail', user.email);
    const name = [user.firstName, user.lastName].filter(Boolean).join(' ').trim();
    if (name) localStorage.setItem('patientUserName', name);
  }
});
