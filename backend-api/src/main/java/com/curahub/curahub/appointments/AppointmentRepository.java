package com.curahub.curahub.appointments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
    List<Appointments> getAppointmentsByDoctor_id(long id);
    List<Appointments> getAppointmentsByUser_id(long id);
    Appointments getAppointmentsById(long id);
}
