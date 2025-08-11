package companymanager.admin.services;

import companymanager.admin.entities.User;
import companymanager.admin.models.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for User business logic
 */
@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Get user by ID
     * @param id the user ID
     * @return Optional containing the user if found
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    /**
     * Get user by EGN
     * @param egn the EGN to search for
     * @return Optional containing the user if found
     */
    public Optional<User> getUserByEgn(String egn) {
        return userRepository.findByEgn(egn);
    }
    
    /**
     * Create a new user
     * @param user the user to create
     * @return the created user
     */
    public User createUser(User user) {
        // Check if EGN already exists
        if (userRepository.existsByEgn(user.getEgn())) {
            throw new RuntimeException("User with EGN " + user.getEgn() + " already exists");
        }
        return userRepository.save(user);
    }
    
    /**
     * Update an existing user
     * @param id the user ID
     * @param userDetails the updated user details
     * @return the updated user
     */
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Check if EGN is being changed and if the new EGN already exists
        if (!user.getEgn().equals(userDetails.getEgn()) && 
            userRepository.existsByEgn(userDetails.getEgn())) {
            throw new RuntimeException("User with EGN " + userDetails.getEgn() + " already exists");
        }
        
        user.setFirstName(userDetails.getFirstName());
        user.setSecondName(userDetails.getSecondName());
        user.setLastName(userDetails.getLastName());
        user.setEgn(userDetails.getEgn());
        
        return userRepository.save(user);
    }
    
    /**
     * Delete a user
     * @param id the user ID
     */
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
    
    /**
     * Search users by first name
     * @param firstName the first name to search for
     * @return List of users with matching first name
     */
    public List<User> searchUsersByFirstName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    /**
     * Search users by last name
     * @param lastName the last name to search for
     * @return List of users with matching last name
     */
    public List<User> searchUsersByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }
}
