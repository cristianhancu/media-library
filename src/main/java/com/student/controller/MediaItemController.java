package com.student.library.controller;

import com.student.library.model.MediaItem;
import com.student.library.model.MediaType;
import com.student.library.repository.MediaItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/media")

public class MediaItemController {

    private final MediaItemRepository mediaItemRepository;

    public MediaItemController(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

    @GetMapping("/all")
    public List<MediaItem> getAllEntries() {
        return mediaItemRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MediaItem> getEntryById(@PathVariable Long id) {
        return mediaItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/title/{title}")
    public ResponseEntity<MediaItem> getEntryByTitle(@PathVariable String title) {
        return mediaItemRepository.findByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }  

    @GetMapping("/releaseYear/{releaseYear}")
    public ResponseEntity<List<MediaItem>> getEntriesByReleaseYear(@PathVariable int releaseYear) {
        List<MediaItem> items = mediaItemRepository.findByReleaseYear(releaseYear).orElse(null);
        if (items == null || items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }  

    @GetMapping("/type/{type}")
    public ResponseEntity<List<MediaItem>> getEntriesByType(@PathVariable MediaType type) {
        List<MediaItem> items = mediaItemRepository.findByType(type).orElse(null);
        if (items == null || items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MediaItem>> getEntriesByGenre(@PathVariable String genre) {
        List<MediaItem> items = mediaItemRepository.findByGenre(genre);
        if (items == null || items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllEntries() {
        mediaItemRepository.deleteAll();
    }

    @DeleteMapping("/deleteId/{id}")
    public ResponseEntity<MediaItem> deleteEntryById(@PathVariable Long id) {
        MediaItem mediaItem = mediaItemRepository.findById(id).orElse(null);
        if (mediaItem == null) {
            return ResponseEntity.notFound().build();
        }
        mediaItemRepository.deleteById(id);
        return ResponseEntity.ok(mediaItem);
    }

    @DeleteMapping("/deleteTitle/{title}")
    public ResponseEntity<MediaItem> deleteEntryByTitle(@PathVariable String title) {
        MediaItem mediaItem = mediaItemRepository.findByTitle(title).orElse(null);
        if (mediaItem == null) {
            return ResponseEntity.notFound().build();
        }
        mediaItemRepository.delete(mediaItem);
        return ResponseEntity.ok(mediaItem);
    }

    @DeleteMapping("/deleteReleaseYear/{releaseYear}")
    public ResponseEntity<List<MediaItem>> deleteEntriesByReleaseYear(@PathVariable int releaseYear) {
        List<MediaItem> items = mediaItemRepository.findByReleaseYear(releaseYear).orElse(null);
        if (items == null || items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mediaItemRepository.deleteAll(items);
        return ResponseEntity.ok(items);
    }  

    @DeleteMapping("/deleteType/{type}")
    public ResponseEntity<List<MediaItem>> deleteEntriesByType(@PathVariable MediaType type) {
        List<MediaItem> items = mediaItemRepository.findByType(type).orElse(null);
        if (items == null || items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mediaItemRepository.deleteAll(items);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/deleteGenre/{genre}")
    public ResponseEntity<List<MediaItem>> deleteEntriesByGenre(@PathVariable String genre) {
        List<MediaItem> items = mediaItemRepository.findByGenre(genre);
        if (items == null || items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mediaItemRepository.deleteAll(items);
        return ResponseEntity.ok(items);
    }

}