document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('loginForm');
  const emailInput = document.getElementById('loginEmail');
  const passwordInput = document.getElementById('loginPassword');
  const submitBtn = document.getElementById('loginSubmit');
  const feedback = document.getElementById('loginFeedback');

  if (!form) return;

  form.addEventListener('submit', async (event) => {
    event.preventDefault();
    clearFeedback();

    const email = emailInput.value.trim().toLowerCase();
    if (!email) {
      showFeedback('Please enter your email address.', true);
      return;
    }

    setBusy(true);
    try {
      const users = await fetchJSON('/users');
      const match = Array.isArray(users)
        ? users.find((user) => (user.email || '').toLowerCase() === email)
        : null;

      if (!match) {
        showFeedback('No account found with that email. Please sign up first.', true);
        return;
      }

      cacheUser(match);
      showFeedback('Logged in successfully. Redirecting…', false);
      setTimeout(() => {
        window.location.href = './dashboard_patient.html';
      }, 800);
    } catch (error) {
      console.error('Login failed', error);
      showFeedback(`Unable to log in (HTTP ${error.status ?? 'Error'})`, true);
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

  function showFeedback(message, isError) {
    if (!feedback) return;
    feedback.textContent = message;
    feedback.classList.toggle('muted', !isError);
    feedback.style.color = isError ? '#fca5a5' : '#34d399';
  }

  function clearFeedback() {
    if (!feedback) return;
    feedback.textContent = '';
    feedback.classList.add('muted');
    feedback.style.color = '';
  }

  function setBusy(busy) {
    if (!submitBtn) return;
    submitBtn.disabled = busy;
    submitBtn.textContent = busy ? 'Checking…' : 'Log In';
  }
});


