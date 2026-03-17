package com.student.library.repository;

import com.student.library.model.MediaItem;
import com.student.library.model.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {
    // Custom query methods
    Optional<MediaItem> findByTitle(String title);
    Optional<List<MediaItem>> findByReleaseYear(int releaseYear);
    Optional<List<MediaItem>> findByType(MediaType type);
    List<MediaItem> findByGenre(String genre);
    void deleteByType(MediaType type);
}