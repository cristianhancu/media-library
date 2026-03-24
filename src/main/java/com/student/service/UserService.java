package com.student.service;

import com.student.model.RoleType;
import com.student.model.User;
import com.student.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        if(user.getRole() == null){
            user.setRole(RoleType.USER);
        }
    return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByUsername(String username) {
    return userRepository.findByUsername(username).orElse(null);
}

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser, RoleType role) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setCreatedAt(updatedUser.getCreatedAt());
                    user.setRole(role);

                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
                    user.setPassword(hashedPassword);
                }
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
     public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}