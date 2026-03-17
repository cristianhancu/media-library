@RestController
@RequestMapping("/library")

    private final LibraryEntryRepository libraryEntryRepository;

    public MediaLibraryController(LibraryEntryRepository libraryEntryRepository) {
        this.libraryEntryRepository = libraryEntryRepository;
    }

    @GetMapping
    public List<LibraryEntry> getAllEntries() {
        return libraryEntryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryEntry> getEntryById(@PathVariable Long id) {
        return libraryEntryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

}