@Service
public class MediaItemService {

    private final MediaItemRepository mediaItemRepository;

    @Autowired
    public MediaItemService(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

        public MediaItem createMediaItem(MediaItem mediaItem) {
        return mediaItemRepository.save(mediaItem);
    }
    
    public MediaItem getMediaItemById(Long id) {
        return mediaItemRepository.findById(id).orElse(null);
    }

    public MediaItem deleteMediaItembyId(Long id) {
        MediaItem mediaItem = mediaItemRepository.findById(id).orElse(null);
        if (mediaItem != null) {
            mediaItemRepository.deleteById(id);
        }
        return mediaItem;
    }

    public MediaItem getMediaItemByTitle(String title) {
        return mediaItemRepository.findByTitle(title).orElse(null);
    }

    public List<MediaItem> getAllMediaItems() {
        return mediaItemRepository.findAll();
    }

    public MediaItem updateMediaItem(Long id, MediaItem updatedMediaItem) {
        return mediaItemRepository.findById(id)
                .map(mediaItem -> {
                    mediaItem.setTitle(updatedMediaItem.getTitle());
                    mediaItem.setAuthor(updatedMediaItem.getAuthor());
                    mediaItem.setType(updatedMediaItem.getType());
                    return mediaItemRepository.save(mediaItem);
                })
                .orElse(null);
    }

    public List<MediaItem> getMediaItemsByReleaseYear(int releaseYear) {
        return mediaItemRepository.findByReleaseYear(releaseYear).orElse(null);
    }

    public List<MediaItem> getMediaItemsByType(MediaType type) {
        return mediaItemRepository.findByType(type).orElse(null);
    }

    public List<MediaItem> getMediaItemsByGenre(String genre) {
        return mediaItemRepository.findByGenre(genre);
    }

    public MediaItem saveMediaItem(MediaItem mediaItem) {
        return mediaItemRepository.save(mediaItem);
    }

    public void deleteMediaItem(Long id) {
        mediaItemRepository.deleteById(id);
    }

    public void deleteAllMediaItems() {
        mediaItemRepository.deleteAll();
    }
    
    public void deleteMediaItemsByType(MediaType type) {
        mediaItemRepository.deleteByType(type);
    }
}