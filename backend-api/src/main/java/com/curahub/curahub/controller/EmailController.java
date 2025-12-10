package com.curahub.curahub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curahub.curahub.doctor.Doctor;
import com.curahub.curahub.service.EmailService;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/api/email/send")
    public String sendEmail(String to, String subject, String text) {
        emailService.sendEmail(to, subject, text);
        return "";
    }

    @PostMapping("/api/doctors/email/cancel")
    public String sendCancelEmail(String to, String subject, String text, long apID) {
        emailService.sendEmail(to, subject, text);
        return "redirect:/api/appointments/doctor/cancel/" + apID;
    }
}

