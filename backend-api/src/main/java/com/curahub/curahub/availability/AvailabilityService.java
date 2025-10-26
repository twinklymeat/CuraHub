package com.curahub.curahub.availability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    public Object getAvailabilityByDoctor(long id) {
        return availabilityRepository.getAvailabilityByDoctorID_id(id);
    }

    public Availability getAvailabilityByID(@PathVariable long id) {
        return availabilityRepository.getAvailabilityById(id);
    }

    public Availability addAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(long id, Availability availability) {
        return availabilityRepository.save(availability);
    }

    public void deleteAvailabilityByID(long id) {
        availabilityRepository.deleteById(id);
    }

    



}
