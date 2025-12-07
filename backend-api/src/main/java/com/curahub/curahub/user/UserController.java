package com.curahub.curahub.user;

// import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.curahub.curahub.doctor.DoctorController;
import com.curahub.curahub.doctor.DoctorRepository;
import com.curahub.curahub.doctor.DoctorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserByID(id));
    }

    @PostMapping(path = "/api/users/doctors", consumes = "application/x-www-form-urlencoded")
    public Object addDoctor(User user) {
        User newUser = userService.addUser(user);
        return "redirect:/doctor/signup1/" + newUser.getID();
    }

    // @PostMapping(consumes = "application/x-www-form-urlencoded")
    // public ResponseEntity<Object> addUser(User user) {
    //     return ResponseEntity.ok(userService.addUser(user));
    // }

    @PostMapping(path = "/api/users/doctors/update/{id}", consumes = "application/x-www-form-urlencoded")
    public Object updateDoctor(User user,@PathVariable long id) {
        userService.updateUser(id, user);
        User a = user;
        userService.addUser(a);
        return "redirect:/doctor/updated/" + id;
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping("/doctor/patient/{id}")
    public Object viewPatient(Model model, @PathVariable long id, @RequestParam long drID) {
        model.addAttribute("user", userService.getUserByID(id));
        model.addAttribute("doctorID", drID);
        model.addAttribute("appointments", userService.getAppointmentsByUser(id));
        return "provider/patientProfile";
    }

}
