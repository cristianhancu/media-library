package com.student.repository;

import com.student.library.Model.LibraryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryEntryRepository extends JpaRepository<LibraryEntry, Long> {
    // Custom query methods can be added here if needed
}