@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    public User getAllUsersByEmail(String email) {
        return userRepository.findAllByEmail(email).orElse(null);
    }

    public List<User> getAllUsersByCreatedAt(LocalDate createdAt) {
        return userRepository.findAllByCreatedAt(createdAt).orElse(null);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}