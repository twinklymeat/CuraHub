package com.curahub.curahub.availability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    
    // @Query(value = "SELECT * FROM availability av WHERE av.doctor = 1", nativeQuery = true)
    List<Availability> getAvailabilityByDoctorID_id(long id);

    Availability getAvailabilityById(long id);

}
