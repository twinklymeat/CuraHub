document.addEventListener('DOMContentLoaded', () => {
  const btnList = document.getElementById('btnList');
  const listResult = document.getElementById('listResult');

  const formGet = document.getElementById('formGet');
  const getResult = document.getElementById('getResult');
  const btnGet = document.getElementById('btnGet');

  const formCreate = document.getElementById('formCreate');
  const createResult = document.getElementById('createResult');
  const btnCreate = document.getElementById('btnCreate');

  const formUpdate = document.getElementById('formUpdate');
  const updateResult = document.getElementById('updateResult');
  const btnUpdate = document.getElementById('btnUpdate');

  const formDelete = document.getElementById('formDelete');
  const deleteResult = document.getElementById('deleteResult');
  const btnDelete = document.getElementById('btnDelete');

  btnList?.addEventListener('click', async () => {
    setBusy(btnList, true);
    try {
      const data = await fetchJSON('/users');
      renderJSON(listResult, data);
      listResult.classList.remove('muted');
    } catch (e) {
      renderJSON(listResult, { error: e.message, data: e.data ?? null });
      listResult.classList.remove('muted');
    } finally {
      setBusy(btnList, false);
    }
  });

  formGet?.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    setBusy(btnGet, true);
    const id = new FormData(formGet).get('id');
    try {
      const data = await fetchJSON(`/users/${id}`);
      renderJSON(getResult, data);
      getResult.classList.remove('muted');
    } catch (e) {
      renderJSON(getResult, { error: e.message, data: e.data ?? null });
      getResult.classList.remove('muted');
    } finally {
      setBusy(btnGet, false);
    }
  });

  formCreate?.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    setBusy(btnCreate, true);
    const fd = new FormData(formCreate);
    const body = {
      firstName: fd.get('firstName'),
      lastName: fd.get('lastName'),
      email: fd.get('email'),
      phone: fd.get('phone')
    };
    try {
      const data = await fetchJSON('/users', {
        method: 'POST',
        body: JSON.stringify(body)
      });
      renderJSON(createResult, data);
      createResult.classList.remove('muted');
    } catch (e) {
      renderJSON(createResult, { error: e.message, data: e.data ?? null });
      createResult.classList.remove('muted');
    } finally {
      setBusy(btnCreate, false);
    }
  });

  formUpdate?.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    setBusy(btnUpdate, true);
    const fd = new FormData(formUpdate);
    const id = fd.get('id');
    const body = {
      firstName: fd.get('firstName'),
      lastName: fd.get('lastName'),
      email: fd.get('email'),
      phone: fd.get('phone')
    };
    try {
      const data = await fetchJSON(`/users/${id}`, {
        method: 'PUT',
        body: JSON.stringify(body)
      });
      renderJSON(updateResult, data);
      updateResult.classList.remove('muted');
    } catch (e) {
      renderJSON(updateResult, { error: e.message, data: e.data ?? null });
      updateResult.classList.remove('muted');
    } finally {
      setBusy(btnUpdate, false);
    }
  });

  formDelete?.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    setBusy(btnDelete, true);
    const id = new FormData(formDelete).get('id');
    try {
      const data = await fetchJSON(`/users/${id}`, { method: 'DELETE' });
      renderJSON(deleteResult, data);
      deleteResult.classList.remove('muted');
    } catch (e) {
      renderJSON(deleteResult, { error: e.message, data: e.data ?? null });
      deleteResult.classList.remove('muted');
    } finally {
      setBusy(btnDelete, false);
    }
  });
});


