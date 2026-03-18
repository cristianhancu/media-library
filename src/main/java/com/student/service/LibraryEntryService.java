package com.student.service;

import com.student.model.LibraryEntry;
import com.student.model.LibraryStatus;
import com.student.repository.LibraryEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryEntryService {

    private final LibraryEntryRepository libraryEntryRepository;

    public LibraryEntryService(LibraryEntryRepository libraryEntryRepository) {
        this.libraryEntryRepository = libraryEntryRepository;
    }

        public LibraryEntry createLibraryEntry(LibraryEntry libraryEntry) {
        return libraryEntryRepository.save(libraryEntry);
    }

    public List<LibraryEntry> getAllLibraryEntries() {
        return libraryEntryRepository.findAll();
    }

    public LibraryEntry getLibraryEntryById(Long id) {
        return libraryEntryRepository.findById(id).orElse(null);
    }

    public LibraryEntry getLibraryEntryByMediaItemId(String mediaItemId) {
        return libraryEntryRepository.findByMediaItemId(mediaItemId).orElse(null);
    }

    public List<LibraryEntry> getLibraryEntriesByUserId(String userId) {
        return libraryEntryRepository.findByUserId(userId);
    }

    public List<LibraryEntry> getLibraryEntriesByStatus(LibraryStatus status) {
        return libraryEntryRepository.findByStatus(status);
    }

    public void deleteLibraryEntryByStatus(LibraryStatus status) {
        List<LibraryEntry> entries = libraryEntryRepository.findByStatus(status);
        libraryEntryRepository.deleteAll(entries);
    }

    public LibraryEntry updateLibraryEntry(Long id, LibraryEntry updatedLibraryEntry) {
        return libraryEntryRepository.findById(id)
                .map(libraryEntry -> {
                    libraryEntry.setUserId(updatedLibraryEntry.getUserId());
                    libraryEntry.setMediaItemId(updatedLibraryEntry.getMediaItemId());
                    libraryEntry.setStatus(updatedLibraryEntry.getStatus());
                    return libraryEntryRepository.save(libraryEntry);
                })
                .orElse(null);
    }

    public LibraryEntry deleteLibraryEntryById(Long id) {
        LibraryEntry libraryEntry = libraryEntryRepository.findById(id).orElse(null);
        if (libraryEntry != null) {
            libraryEntryRepository.deleteById(id);
        }
        return libraryEntry;
    }

    public void deleteLibraryEntry(Long id) {
        libraryEntryRepository.deleteById(id);
    }

    public void deleteAllLibraryEntries() {
        libraryEntryRepository.deleteAll();
    }
}