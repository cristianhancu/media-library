package com.student.library.repository;

import com.student.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    void deleteByName(String name);
    void deleteByEmail(String email);
}