package com.curahub.curahub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curahub.curahub.service.EmailService;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/api/email/send")
    public String sendEmail(
        @RequestParam("to") String to,
        @RequestParam(value = "subject", required = false, defaultValue = "") String subject,
        @RequestParam(value = "text", required = false, defaultValue = "") String text
    ) {
        emailService.sendEmail(to, subject, text);
        // Return a simple acknowledgement so the client gets 200 instead of view resolution 404.
        return "sent";
    }

    @PostMapping("/api/doctors/email/cancel")
    public String sendCancelEmail(
        @RequestParam("to") String to,
        @RequestParam(value = "subject", required = false, defaultValue = "") String subject,
        @RequestParam(value = "text", required = false, defaultValue = "") String text,
        @RequestParam("apID") long apID
    ) {
        emailService.sendEmail(to, subject, text);
        return "redirect:/api/appointments/doctor/cancel/" + apID;
    }
}

