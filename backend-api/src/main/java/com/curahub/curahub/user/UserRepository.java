package com.curahub.curahub.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.curahub.curahub.appointments.Appointments;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User getUserById(long id);
    User getUserByEmail(String email);
    List<User> getUserByPhone(String phone);
    List<User> getUserByFirstName(String firstName);
    List<User> getUserByLastName(String lastName);
    List<User> getUserByName(String name);

    @Query(value = "SELECT * FROM appointments WHERE user_id = :id", nativeQuery = true)
    List<Appointments> getAppointmentsByUser(long id);
}
