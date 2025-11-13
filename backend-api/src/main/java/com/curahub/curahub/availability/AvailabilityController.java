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
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/availability")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<Object> getAllAvailability() {
        return ResponseEntity.ok(availabilityService.getAllAvailability());
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Availability> getAvailabilityByID(@PathVariable long id) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByID(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Object> getAvailabilityByDoctor(@PathVariable long id) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByDoctor(id));
    }

    @PostMapping
    public ResponseEntity<Object> addAvailability(@RequestBody Availability availability) {
        return ResponseEntity.ok(availabilityService.addAvailability(availability));
    }

    @DeleteMapping("/{id}")
    public Object deleteAvailability(@PathVariable long id) {
        availabilityService.deleteAvailabilityByID(id);
        return availabilityService.getAllAvailability();
    }

}
