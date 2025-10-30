package com.curahub.curahub.appointments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointments> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointments> getAppointmentsByDoctor(long doctorId) {
        return appointmentRepository.getAppointmentsByDoctor_id(doctorId);
    }

    public Appointments getAppointmentById(long id) {
        return appointmentRepository.getAppointmentsById(id);
    }

    public Appointments addAppointment(Appointments appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }
}
