const API_BASE = 'http://localhost:8080/api';

async function fetchJSON(path, options = {}) {
  const url = path.startsWith('http') ? path : `${API_BASE}${path}`;
  const init = {
    headers: {
      'Content-Type': 'application/json'
    },
    ...options
  };
  const res = await fetch(url, init);
  const text = await res.text();
  let data = null;
  try {
    data = text ? JSON.parse(text) : null;
  } catch {
    data = text;
  }
  if (!res.ok) {
    const err = new Error(`HTTP ${res.status}`);
    err.status = res.status;
    err.data = data;
    throw err;
  }
  return data;
}

function renderJSON(node, data) {
  node.textContent = JSON.stringify(data, null, 2);
}

function setBusy(button, isBusy) {
  if (!button) return;
  if (isBusy) {
    button.disabled = true;
    button.dataset.prevText = button.textContent;
    button.textContent = 'Working...';
  } else {
    button.disabled = false;
    if (button.dataset.prevText) button.textContent = button.dataset.prevText;
  }
}

function parseDateTimeLocalToSeconds(datetimeLocal) {
  // input type="datetime-local" usually returns "YYYY-MM-DDTHH:mm"
  if (!datetimeLocal) return '';
  const hasSeconds = datetimeLocal.length === 19;
  return hasSeconds ? datetimeLocal : `${datetimeLocal}:00`;
}


