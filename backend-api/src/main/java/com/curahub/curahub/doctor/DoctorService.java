package com.curahub.curahub.doctor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// import java.util.List;

// import java.io.IOException;

// import java.io.File;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.curahub.curahub.appointments.Appointments;
import com.curahub.curahub.reviews.Reviews;

// import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Object getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorByID(@PathVariable long id) {
        return doctorRepository.getDoctorById(id);
    }

    public Doctor getDoctorByUserID(long id) {
        return doctorRepository.getBydoctorUser(id);
    }

    public Object getAvailabilities(long id) {
        return doctorRepository.getAvailabilitiesByDoctor(id);
    }

    // public Object getDoctorByDescription(String description) {
    // return doctorRepository.getDoctorsByDescription(description);
    // }

    public Object getAppointments(long id) {
        return doctorRepository.getAppointments(id);
    }

    public List<Appointments> getUpcomingAppointments(long id) {
        List<Appointments> appts = doctorRepository.getAppointments(id);
        List<Appointments> newAppts = doctorRepository.getAppointments(id);
        int offset = 0;
        for (int i = 0; i < appts.size(); i++) {
            if (appts.get(i).getCompleted() == false) {
                newAppts.remove(i - offset);
                offset += 1;
            }
        };
        newAppts.sort(Comparator.comparing(Appointments::getTime));
        return newAppts;
    }

    public List<Appointments> getPastAppointments(long id) {
        List<Appointments> appts = doctorRepository.getAppointments(id);
        List<Appointments> newAppts = doctorRepository.getAppointments(id);
        int offset = 0;
        for (int i = 0; i < appts.size(); i++) {
            if (appts.get(i).getCompleted() == true) {
                newAppts.remove(i - offset);
                offset += 1;
            }
        };
        newAppts.sort(Comparator.comparing(Appointments::getTime).reversed());
        return newAppts;
    }
    
    public double getAverageReviews(long id) {
        List<Reviews> revs = getReviewsByDoctor(id);
        long ratings = 0; 
        for (int r = 0; r < revs.size(); r++) {
            ratings += revs.get(r).getRating();
        }
        return ((double)ratings) / ((double)revs.size());
    }

    public Object getDoctorByName(String name) {
        return doctorRepository.getDoctorByName(name);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }


    public Doctor loginAsDoctors(String email, String password) {

        return doctorRepository.loginAsDoctor(email, password);
    }

    public List<Reviews> getReviewsByDoctor(long id) {
        return doctorRepository.getReviewsByDoctorID(id);
    }

}
