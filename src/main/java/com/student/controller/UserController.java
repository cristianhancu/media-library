package com.student.library.controller;

import com.student.library.model.User;
import com.student.library.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getAllEntries() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getEntryById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getEntryByName(@PathVariable String name) {
        return userRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getEntryByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser/{id}")
    public User addUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @PostMapping("/addUser/{name}")
    public User addUserByName(@PathVariable String name, @RequestBody User user) {
        user.setName(name);
        return userRepository.save(user);
    }

    @PostMapping("/addUser/{email}")
    public User addUserByEmail(@PathVariable String email, @RequestBody User user) {
        user.setEmail(email);
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

     @PutMapping("/updateUser/{name}")
    public User updateUserByName(@PathVariable String name, @RequestBody User user) {
        return userRepository.findByName(name)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }   

    @PutMapping("/updateUser/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User user) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    @DeleteMapping("/deleteUser/all")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping("/deleteUser/{name}")
    public void deleteUserByName(@PathVariable String name) {
        userRepository.deleteByName(name);
    }

    @DeleteMapping("/deleteUser/{email}")
    public void deleteUserByEmail(@PathVariable String email) {    
        userRepository.deleteByEmail(email);
    }
}