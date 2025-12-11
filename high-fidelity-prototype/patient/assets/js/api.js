const API_BASE = 'http://localhost:8080/api';

async function fetchJSON(path, options = {}) {
  const url = path.startsWith('http') ? path : `${API_BASE}${path}`;
  const hasBody = options.body !== undefined && options.body !== null;
  const headers = { ...(options.headers || {}) };
  if (hasBody && !headers['Content-Type']) {
    headers['Content-Type'] = 'application/json';
  }
  const response = await fetch(url, { ...options, headers });
  if (!response.ok) {
    const error = new Error(`Request failed: ${response.status}`);
    error.status = response.status;
    error.body = await response.text().catch(() => '');
    throw error;
  }
  const text = await response.text();
  return text ? JSON.parse(text) : null;
}

// Fire-and-forget email helper; backend responds with an empty body.
async function sendEmail({ to, subject, text }) {
  if (!to) throw new Error('Missing recipient email');
  const params = new URLSearchParams({ to, subject, text });
  const url = `${API_BASE}/email/send?${params.toString()}`;
  const res = await fetch(url, { method: 'GET' });
  if (!res.ok) {
    const body = await res.text().catch(() => '');
    const error = new Error(`Email send failed: ${res.status}`);
    error.status = res.status;
    error.body = body;
    throw error;
  }
  return true;
}

// Helper to pull cached patient info set during login/signup.
function getCachedPatientInfo() {
  return {
    id: localStorage.getItem('patientUserId'),
    email: localStorage.getItem('patientUserEmail'),
    name: localStorage.getItem('patientUserName')
  };
}