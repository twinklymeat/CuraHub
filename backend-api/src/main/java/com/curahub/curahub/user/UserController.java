package com.curahub.curahub.user;

// import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserByID(id));
    }
    
    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
}
