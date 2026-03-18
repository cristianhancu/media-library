package com.student.controller;

import com.student.repository.LibraryEntryRepository;
import com.student.model.LibraryEntry;
import com.student.model.LibraryStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")

public class LibraryEntryController {

    private final LibraryEntryRepository libraryEntryRepository;

    public LibraryEntryController(LibraryEntryRepository libraryEntryRepository) {
        this.libraryEntryRepository = libraryEntryRepository;
    }

    @GetMapping("/all")
    public List<LibraryEntry> getAllEntries() {
        return libraryEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryEntry> getEntryById(@PathVariable Long id) {
        return libraryEntryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<LibraryEntry>> getEntriesByUserId(@PathVariable String userId) {
        List<LibraryEntry> entries = libraryEntryRepository.findByUserId(userId);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{mediaItemId}")
    public ResponseEntity<LibraryEntry> getEntryByMediaItemId(@PathVariable String mediaItemId) {
        return libraryEntryRepository.findByMediaItemId(mediaItemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<LibraryEntry>> getEntriesByStatus(@PathVariable LibraryStatus status) {
        List<LibraryEntry> entries = libraryEntryRepository.findByStatus(status);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{rating}")
    public ResponseEntity<List<LibraryEntry>> getEntriesByRating(@PathVariable Integer rating) {
        List<LibraryEntry> entries = libraryEntryRepository.findByRating(rating);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{addedAt}")
    public ResponseEntity<List<LibraryEntry>> getEntriesByAddedAt(@PathVariable Integer addedAt) {
        List<LibraryEntry> entries = libraryEntryRepository.findByAddedAt(addedAt);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryEntry> updateEntry(@PathVariable Long id, @RequestBody LibraryEntry updatedEntry) {
        return libraryEntryRepository.findById(id)
                .map(entry -> {
                    entry.setUserId(updatedEntry.getUserId());
                    entry.setMediaItemId(updatedEntry.getMediaItemId());
                    entry.setStatus(updatedEntry.getStatus());
                    entry.setRating(updatedEntry.getRating());
                    entry.setAddedAt(updatedEntry.getAddedAt());
                    return ResponseEntity.ok(libraryEntryRepository.save(entry));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{rating}")
    public ResponseEntity<LibraryEntry> createEntry(@RequestBody LibraryEntry newEntry) {
        LibraryEntry savedEntry = libraryEntryRepository.save(newEntry);
        return ResponseEntity.ok(savedEntry);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllEntries() {
        libraryEntryRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LibraryEntry> deleteEntryById(@PathVariable Long id) {
        LibraryEntry entry = libraryEntryRepository.findById(id).orElse(null);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        libraryEntryRepository.deleteById(id);
        return ResponseEntity.ok(entry);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<List<LibraryEntry>> deleteEntriesByUserId(@PathVariable String userId) {
        List<LibraryEntry> entries = libraryEntryRepository.findByUserId(userId);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libraryEntryRepository.deleteAll(entries);
        return ResponseEntity.ok(entries);
    }

    @DeleteMapping("/{mediaItemId}")
    public ResponseEntity<LibraryEntry> deleteEntryByMediaItemId(@PathVariable String mediaItemId) {
        LibraryEntry entry = libraryEntryRepository.findByMediaItemId(mediaItemId).orElse(null);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        libraryEntryRepository.delete(entry);
        return ResponseEntity.ok(entry);
    }

    @DeleteMapping("/{status}")
    public ResponseEntity<List<LibraryEntry>> deleteEntriesByStatus(@PathVariable LibraryStatus status) {
        List<LibraryEntry> entries = libraryEntryRepository.findByStatus(status);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libraryEntryRepository.deleteAll(entries);
        return ResponseEntity.ok(entries);
    }

    @DeleteMapping("/{rating}")
    public ResponseEntity<List<LibraryEntry>> deleteEntriesByRating(@PathVariable Integer rating) {
        List<LibraryEntry> entries = libraryEntryRepository.findByRating(rating);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libraryEntryRepository.deleteAll(entries);
        return ResponseEntity.ok(entries);
    }

    @DeleteMapping("/{addedAt}")
    public ResponseEntity<List<LibraryEntry>> deleteEntriesByAddedAt(@PathVariable Integer addedAt) {
        List<LibraryEntry> entries = libraryEntryRepository.findByAddedAt(addedAt);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libraryEntryRepository.deleteAll(entries);
        return ResponseEntity.ok(entries);
    }
}