package com.student.repository;

import com.student.model.RoleType;
import com.student.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<List<User>> findByRole(RoleType role);
    void deleteByUsername(String username);
    void deleteByEmail(String email);
}