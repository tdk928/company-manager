package companymanager.admin.models;

import companymanager.admin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity operations
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by EGN
     * @param egn the EGN to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByEgn(String egn);
    
    /**
     * Check if user exists by EGN
     * @param egn the EGN to check
     * @return true if user exists, false otherwise
     */
    boolean existsByEgn(String egn);
    
    /**
     * Find users by first name
     * @param firstName the first name to search for
     * @return List of users with matching first name
     */
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    
    /**
     * Find users by last name
     * @param lastName the last name to search for
     * @return List of users with matching last name
     */
    List<User> findByLastNameContainingIgnoreCase(String lastName);
}
