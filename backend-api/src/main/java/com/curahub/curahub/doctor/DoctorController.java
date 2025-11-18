package com.curahub.curahub.doctor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// import java.util.List;

// import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.HttpExchange;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/api/doctors")
    public ResponseEntity<Object> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/doctor/dashboard/{id}")
    public Object getDashboard(Model model, @PathVariable long id) {
        model.addAttribute("doctor", doctorService.getDoctorByID(id));
        model.addAttribute("availability", doctorService.getAvailabilities(id));
        model.addAttribute("appointments", doctorService.getAppointments(id));
        model.addAttribute("title", "Doctor Dashboard");
        return "provider/homepage";
    }

    @GetMapping("/doctor/login")
    public Object getLoginPage(Model model) {
        return "provider/login";
    }

    @GetMapping("/doctor/login/validate")
    public Object validate(Model model, @RequestParam String email, @RequestParam String password) {
        // String email = credentials.get("email");
        // String password = credentials.get("password");

        Doctor doctor = doctorService.loginAsDoctors(email, password);
        if (doctor == null) {
            return "redirect:/doctor/login";
        }
        return "redirect:/doctor/dashboard/" + doctor.getID();
    }

    @GetMapping("/api/doctors/{id}")
    public ResponseEntity<Object> getDoctorByID(@PathVariable long id) {
        return ResponseEntity.ok(doctorService.getDoctorByID(id));
    }

    @GetMapping("/api/doctors/search")
    public ResponseEntity<Object> getDoctorsByName(@RequestParam String name) {
        return ResponseEntity.ok(doctorService.getDoctorByName(name));
    }

    @PostMapping(path = "/api/doctors", consumes = "application/json")
    public Object addDoctor(Model model, @RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctor/dashboard/" + doctor.getID();
    }

    @PostMapping(path = "/api/doctors", consumes = "application/x-www-form-urlencoded")
    public Object addDoctor2(Doctor doctor) {
        // return doctorService.addDoctor(doctor);
        doctorService.addDoctor(doctor);
        return "redirect:/doctor/dashboard/" + doctor.getID();
    }

    @GetMapping("/doctor/signup1")
    public Object doctorSignup1(Model model) {
        return "provider/signup";
    }

    @GetMapping("/doctor/signup1/{id}")
    public Object doctorSignup2(Model model, @PathVariable long id) {
        model.addAttribute("userID", id);
        return "provider/signupP2";
    }

    @DeleteMapping("/api/doctors/{id}")
    public Object deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return doctorService.getAllDoctors();
    }

}
