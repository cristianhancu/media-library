@Service
public class LibraryEntryService {

    private final LibraryEntryRepository libraryEntryRepository;

    @Autowired
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

    public LibraryEntry getLibraryEntryByMediaItemId(Long mediaItemId) {
        return libraryEntryRepository.findByMediaItemId(mediaItemId).orElse(null);
    }

    public List<LibraryEntry> getLibraryEntriesByUserId(Long userId) {
        return libraryEntryRepository.findByUserId(userId);
    }

    public List<LibraryEntry> getLibraryEntriesByStatus(String status) {
        return libraryEntryRepository.findByStatus(status);
    }

    public void deleteLibraryEntryByStatus(String status) {
        libraryEntryRepository.deleteByStatus(status);
    }

    public LibraryEntry updateLibraryEntry(Long id, LibraryEntry updatedLibraryEntry) {
        return libraryEntryRepository.findById(id)
                .map(libraryEntry -> {
                    libraryEntry.setUser(updatedLibraryEntry.getUser());
                    libraryEntry.setMediaItem(updatedLibraryEntry.getMediaItem());
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