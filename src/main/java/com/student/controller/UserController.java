package com.student.controller;

import com.student.model.RoleType;
import com.student.model.User;
import com.student.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllEntries() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getEntryById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user)
                           : ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user)
                    : ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return user != null ? ResponseEntity.ok(user)
                           : ResponseEntity.notFound().build();
    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {

    User existingUser = userService.getUserByUsername(user.getUsername());
    if (existingUser == null) {
        return ResponseEntity.status(401).body("User not found");
    }
    if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
        return ResponseEntity.status(401).body("Wrong password");
    }
    return ResponseEntity.ok(existingUser);
}

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {

    User existingUser = userService.getUserByUsername(user.getUsername());

    if (existingUser != null) {
        return ResponseEntity.status(409).body("User already exists");
    }
    user.setRole(user.getRole() != null ? user.getRole() : RoleType.USER);
    User savedUser = userService.createUser(user);

    return ResponseEntity.status(201).body(savedUser);
}
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user, user.getRole());
        return updated != null ? ResponseEntity.ok(updated)
                               : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/deleteid/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/deleteusername/{username}")
    public void deleteUserByName(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }

    @DeleteMapping("/deleteemail/{email}")
    public void deleteUserByEmail(@PathVariable String email) {    
        userService.deleteUserByEmail(email);
    }
}