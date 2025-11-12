document.addEventListener('DOMContentLoaded', () => {
  const btnList = document.getElementById('btnListAvail');
  const listResult = document.getElementById('availListResult');

  const formGet = document.getElementById('formGetAvail');
  const btnGet = document.getElementById('btnGetAvail');
  const getResult = document.getElementById('availGetResult');

  const formGetByDoctor = document.getElementById('formGetAvailByDoctor');
  const btnGetByDoctor = document.getElementById('btnGetAvailByDoctor');
  const getDoctorResult = document.getElementById('availGetDoctorResult');

  btnList?.addEventListener('click', async () => {
    setBusy(btnList, true);
    try {
      const data = await fetchJSON('/availability');
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
      const data = await fetchJSON(`/availability/${id}`);
      renderJSON(getResult, data);
      getResult.classList.remove('muted');
    } catch (e) {
      renderJSON(getResult, { error: e.message, data: e.data ?? null });
      getResult.classList.remove('muted');
    } finally {
      setBusy(btnGet, false);
    }
  });

  formGetByDoctor?.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    setBusy(btnGetByDoctor, true);
    const doctorId = new FormData(formGetByDoctor).get('doctorId');
    try {
      const data = await fetchJSON(`/availability/doctor/${doctorId}`);
      renderJSON(getDoctorResult, data);
      getDoctorResult.classList.remove('muted');
    } catch (e) {
      renderJSON(getDoctorResult, { error: e.message, data: e.data ?? null });
      getDoctorResult.classList.remove('muted');
    } finally {
      setBusy(btnGetByDoctor, false);
    }
  });
});


