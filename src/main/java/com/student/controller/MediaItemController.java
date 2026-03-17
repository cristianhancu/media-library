package com.student.controller;

import com.student.library.Model.User;
import com.student.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/media")

    private final MediaItemRepository mediaItemRepository;

    public MediaLibraryController(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

    @GetMapping
    public List<MediaItem> getAllEntries() {
        return mediaItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaItem> getEntryById(@PathVariable Long id) {
        return mediaItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

}