document.addEventListener('DOMContentLoaded', () => {
  const btnList = document.getElementById('btnListAppointments');
  const listResult = document.getElementById('appointmentsListResult');

  const formCreate = document.getElementById('formCreateAppointment');
  const btnCreate = document.getElementById('btnCreateAppointment');
  const createResult = document.getElementById('appointmentCreateResult');

  btnList?.addEventListener('click', async () => {
    setBusy(btnList, true);
    try {
      const data = await fetchJSON('/appointments');
      renderJSON(listResult, data);
      listResult.classList.remove('muted');
    } catch (e) {
      renderJSON(listResult, { error: e.message, data: e.data ?? null });
      listResult.classList.remove('muted');
    } finally {
      setBusy(btnList, false);
    }
  });

  formCreate?.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    setBusy(btnCreate, true);
    const fd = new FormData(formCreate);
    const body = {
      time: parseDateTimeLocalToSeconds(fd.get('time')),
      doctor: { id: Number(fd.get('doctorId')) },
      user: { id: Number(fd.get('userId')) }
    };
    try {
      const data = await fetchJSON('/appointments', {
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
});


