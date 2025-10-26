package com.curahub.curahub.doctor;

// import java.io.IOException;

// import java.io.File;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

// import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Object getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Object getDoctorByID(@PathVariable long id) {
        return doctorRepository.getDoctorById(id);
    }

    // public Object getDoctorByDescription(String description) {
    //     return doctorRepository.getDoctorsByDescription(description);
    // }

    public Object getDoctorByName(String name){
        return doctorRepository.getByuser_Name(name);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }

}
