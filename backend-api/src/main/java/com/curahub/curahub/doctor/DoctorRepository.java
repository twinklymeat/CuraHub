package com.curahub.curahub.doctor;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    // @Query(value = "select * from doctor s where s.name like %?1% ", nativeQuery = true)
    // List<Doctor> getDoctorsByDescription(String description);
    
    // @Query(value = "SELECT dr.id, dr.doctor_user, bu.email, bu.first_name, bu.last_name, bu.phone, dr.description FROM doctor dr JOIN base_user bu ON dr.doctor_user = bu.id", nativeQuery = true)
    // List<Doctor> getDoctors();

    // @Query(value = "SELECT dr.id, dr.doctor_user, bu.email, bu.first_name, bu.last_name, bu.phone, dr.description FROM doctor dr JOIN base_user bu ON dr.doctor_user = bu.id AND dr.id = %?1%", nativeQuery = true)
    List<Doctor> getDoctorById(long id);

    // @Query(value = "SELECT dr.id, dr.doctor_user, bu.email, bu.first_name, bu.last_name, bu.phone, dr.description FROM doctor dr JOIN base_user bu ON dr.doctor_user = bu.id AND (bu.first_name LIKE %?1% OR bu.last_name LIKE %?1%)")
    List<Doctor> getByuser_Name(@Param("name") String name);

    //I wanna kms T_T
}   
