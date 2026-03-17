package com.student.library.repository;

import com.student.library.model.LibraryEntry;
import com.student.library.model.LibraryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryEntryRepository extends JpaRepository<LibraryEntry, Long> {
    // Custom query methods
    List<LibraryEntry> findByUserId(String userId);
    Optional<LibraryEntry> findByMediaItemId(String mediaItemId);
    List<LibraryEntry> findByStatus(LibraryStatus status);
    List<LibraryEntry> findByRating(Integer rating);
    List<LibraryEntry> findByAddedAt(Integer addedAt);
}