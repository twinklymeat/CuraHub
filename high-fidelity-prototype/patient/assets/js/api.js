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


