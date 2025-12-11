document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('signupForm');
  const feedback = document.getElementById('signupFeedback');
  const submitBtn = document.getElementById('signupSubmit');
  const nameInput = document.getElementById('signupName');
  const emailInput = document.getElementById('signupEmail');
  const phoneInput = document.getElementById('signupPhone');
  const passwordInput = document.getElementById('signupPassword');
  const confirmInput = document.getElementById('signupConfirm');

  if (!form) return;

  form.addEventListener('submit', async (event) => {
    event.preventDefault();
    clearFeedback();

    if (!passwordInput.value || passwordInput.value !== confirmInput.value) {
      showFeedback('Passwords do not match.', true);
      return;
    }

    const fullName = nameInput.value.trim();
    const [firstName, ...rest] = fullName.split(/\s+/);
    const lastName = rest.join(' ');

    if (!firstName || !lastName) {
      showFeedback('Please enter both your first and last name.', true);
      return;
    }

    setBusy(true);
    try {
      const payload = {
        firstName,
        lastName,
        email: emailInput.value.trim(),
        phone: phoneInput.value.trim() || 'N/A'
      };

      const created = await fetchJSON('/users', {
        method: 'POST',
        body: JSON.stringify(payload)
      });

      showFeedback('Account created! Redirecting to login…', false);
      cacheUser(created);
      setTimeout(() => {
        window.location.href = './patient_login.html';
      }, 1200);
    } catch (error) {
      console.error('Signup failed', error);
      const details = error.body ? ` — ${error.body}` : '';
      showFeedback(`Signup failed (HTTP ${error.status ?? 'Error'})${details}`, true);
    } finally {
      setBusy(false);
    }
  });

  function cacheUser(user) {
    if (!user) return;
    if (user.id !== undefined) {
      localStorage.setItem('patientUserId', String(user.id));
    }
    if (user.email) localStorage.setItem('patientUserEmail', user.email);
    const name = [user.firstName, user.lastName].filter(Boolean).join(' ').trim();
    if (name) localStorage.setItem('patientUserName', name);
  }

  function clearFeedback() {
    if (feedback) {
      feedback.textContent = '';
      feedback.classList.add('muted');
    }
  }

  function showFeedback(message, isError) {
    if (!feedback) return;
    feedback.textContent = message;
    feedback.classList.toggle('muted', !isError);
    feedback.style.color = isError ? '#fca5a5' : '#34d399';
  }

  function setBusy(busy) {
    if (!submitBtn) return;
    submitBtn.disabled = busy;
    submitBtn.textContent = busy ? 'Submitting…' : 'Sign Up';
  }
});


