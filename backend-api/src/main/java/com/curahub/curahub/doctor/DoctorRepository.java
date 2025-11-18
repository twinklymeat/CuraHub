package com.curahub.curahub.doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curahub.curahub.appointments.Appointments;
import com.curahub.curahub.availability.Availability;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // @Query(value = "select * from doctor s where s.name like %?1% ", nativeQuery
    // = true)
    // List<Doctor> getDoctorsByDescription(String description);

    @Query(value = "SELECT d.*, u.id AS user_id, u.email, u.first_name, u.last_name, u.name, u.password, u.phone FROM doctor d JOIN base_user u ON d.doctor_user = u.id WHERE u.name LIKE %:name%", nativeQuery = true)
    List<Doctor> getDoctorByName(String name);

    @Query(value = "SELECT * FROM availability WHERE doctor = :id", nativeQuery = true)
    List<Availability> getAvailabilitiesByDoctor(long id);

    @Query(value = "SELECT * FROM appointments WHERE doctor_id = :id", nativeQuery = true)
    List<Appointments> getAppointments(long id);

    @Query(value = "SELECT d.*, u.id AS user_id, u.email, u.first_name, u.last_name, u.name, u.password, u.phone FROM doctor d JOIN base_user u ON d.doctor_user = u.id WHERE u.email = :email AND u.password = :password LIMIT 1", nativeQuery = true)
    Doctor loginAsDoctor(String email, String password);

    List<Doctor> findByuser_emailAndUser_Password(String email, String password);
    // @Query(value = "SELECT dr.id, dr.doctor_user, bu.email, bu.first_name,
    // bu.last_name, bu.phone, dr.description FROM doctor dr JOIN base_user bu ON
    // dr.doctor_user = bu.id", nativeQuery = true)
    // List<Doctor> getDoctors();

    // @Query(value = "SELECT dr.id, dr.doctor_user, bu.email, bu.first_name,
    // bu.last_name, bu.phone, dr.description FROM doctor dr JOIN base_user bu ON
    // dr.doctor_user = bu.id AND dr.id = %?1%", nativeQuery = true)
    Doctor getDoctorById(long id);

    // @Query(value = "SELECT dr.id, dr.doctor_user, bu.email, bu.first_name,
    // bu.last_name, bu.phone, dr.description FROM doctor dr JOIN base_user bu ON
    // dr.doctor_user = bu.id AND (bu.first_name LIKE %?1% OR bu.last_name LIKE
    // %?1%)")
    List<Doctor> getByuser_Name(@Param("name") String name);

    // I wanna kms T_T
}
