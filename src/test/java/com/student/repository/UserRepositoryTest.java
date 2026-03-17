package com.student.repository;

import com.student.library.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        // Given
        User user = new User(null, "John Doe", "john@example.com", 1234567890);

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void testFindByName() {
        // Given
        User user = new User(null, "Jane Doe", "jane@example.com", 1234567890);
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findByName("Jane Doe");

        // Then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Jane Doe");
        assertThat(foundUser.get().getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void testFindByEmail() {
        // Given
        User user = new User(null, "Bob Smith", "bob@example.com", 1234567890);
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findByEmail("bob@example.com");

        // Then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Bob Smith");
        assertThat(foundUser.get().getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    void testDeleteByName() {
        // Given
        User user = new User(null, "Alice Johnson", "alice@example.com", 1234567890);
        userRepository.save(user);

        // When
        userRepository.deleteByName("Alice Johnson");

        // Then
        Optional<User> deletedUser = userRepository.findByName("Alice Johnson");
        assertThat(deletedUser).isNotPresent();
    }

    @Test
    void testDeleteByEmail() {
        // Given
        User user = new User(null, "Charlie Brown", "charlie@example.com", 1234567890);
        userRepository.save(user);

        // When
        userRepository.deleteByEmail("charlie@example.com");

        // Then
        Optional<User> deletedUser = userRepository.findByEmail("charlie@example.com");
        assertThat(deletedUser).isNotPresent();
    }

    @Test
    void testFindByNameNotFound() {
        // When
        Optional<User> foundUser = userRepository.findByName("Nonexistent User");

        // Then
        assertThat(foundUser).isNotPresent();
    }

    @Test
    void testFindByEmailNotFound() {
        // When
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Then
        assertThat(foundUser).isNotPresent();
    }
}