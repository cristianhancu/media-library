package com.student.repository;

import com.student.library.Model.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {
    // Custom query methods can be added here if needed
}