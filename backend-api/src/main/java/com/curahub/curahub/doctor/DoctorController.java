package com.curahub.curahub.doctor;

// import java.util.List;

// import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<Object> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctorByID(@PathVariable long id) {
        return ResponseEntity.ok(doctorService.getDoctorByID(id));
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getDoctorsByName(@RequestParam String name) {
        return ResponseEntity.ok(doctorService.getDoctorByName(name));
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public Object deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return doctorService.getAllDoctors();
    }

}
