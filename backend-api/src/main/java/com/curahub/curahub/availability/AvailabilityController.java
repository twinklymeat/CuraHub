package com.curahub.curahub.availability;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/api/availability")
    public ResponseEntity<Object> getAllAvailability() {
        return ResponseEntity.ok(availabilityService.getAllAvailability());
    }

    @GetMapping("/api/availability/{id}") 
    public ResponseEntity<Availability> getAvailabilityByID(@PathVariable long id) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByID(id));
    }

    @GetMapping("/api/availability/doctor/{id}")
    public ResponseEntity<Object> getAvailabilityByDoctor(@PathVariable long id) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByDoctor(id));
    }

    @PostMapping("/api/availability")
    public ResponseEntity<Object> addAvailability(@RequestBody Availability availability) {
        return ResponseEntity.ok(availabilityService.addAvailability(availability));
    }

    @PostMapping("/api/availability/add")
    public Object addAvailabilityNoJson(Availability availability) {
        availabilityService.addAvailability(availability);
        return "redirect:/doctor/dashboard/" + availability.getDoctor().getID();
    }

    @DeleteMapping("/api/availability/{id}")
    public Object deleteAvailability(@PathVariable long id) {
        availabilityService.deleteAvailabilityByID(id);
        return availabilityService.getAllAvailability();
    }

    @GetMapping("/api/availability/delete/{id}")
    public Object deleteAvailabilityByPost(@PathVariable long id) {
        Availability av = availabilityService.getAvailabilityByID(id);
        long ID = av.getDoctor().getID();
        availabilityService.deleteAvailabilityByID(id);

        return "redirect:/doctor/dashboard/" + ID;
    }

    @GetMapping("/doctor/availability")
    public Object editAvailability(Model model, @RequestParam long avID) {
        model.addAttribute("av", availabilityService.getAvailabilityByID(avID));
        return "provider/editAvailability";
    }

}
