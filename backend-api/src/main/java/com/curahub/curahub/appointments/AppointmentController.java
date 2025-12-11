package com.curahub.curahub.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Object> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getAppointmentsByUser(@PathVariable long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUser(id));
    }

    @PostMapping
    public ResponseEntity<Object> addAppointment(@RequestBody Appointments appointment) {
        return ResponseEntity.ok(appointmentService.addAppointment(appointment));
    }

    @PostMapping("/doctor")
    public Object addDrAppointment(Appointments appointment) {
        appointmentService.addAppointment(appointment);
        return "redirect:/doctor/dashboard/"+appointment.getDoctor().getID();
    }

    @GetMapping("/doctor/cancel/{id}")
    public Object drCancelAppointment(@PathVariable long id) {
        long drID = appointmentService.getAppointmentById(id).getDoctor().getID();
        appointmentService.deleteAppointment(id);
        return "redirect:/doctor/dashboard/" + drID;
    }
}
