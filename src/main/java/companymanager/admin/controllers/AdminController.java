package companymanager.admin.controllers;

import companymanager.admin.entities.User;
import companymanager.admin.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for User management operations
 */
@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "*")
public class AdminController {
    
    private final UserService userService;
    
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * GET - Retrieve all users
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    /**
     * GET - Retrieve user by ID
     * @param id the user ID
     * @return User if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * GET - Retrieve user by EGN
     * @param egn the EGN to search for
     * @return User if found, 404 if not found
     */
    @GetMapping("/egn/{egn}")
    public ResponseEntity<User> getUserByEgn(@PathVariable String egn) {
        Optional<User> user = userService.getUserByEgn(egn);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST - Create a new user
     * @param user the user to create
     * @return Created user with 201 status
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * PUT - Update an existing user
     * @param id the user ID
     * @param userDetails the updated user details
     * @return Updated user if successful, 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * DELETE - Delete a user
     * @param id the user ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET - Search users by first name
     * @param firstName the first name to search for
     * @return List of users with matching first name
     */
    @GetMapping("/search/firstname")
    public ResponseEntity<List<User>> searchUsersByFirstName(@RequestParam String firstName) {
        List<User> users = userService.searchUsersByFirstName(firstName);
        return ResponseEntity.ok(users);
    }
    
    /**
     * GET - Search users by last name
     * @param lastName the last name to search for
     * @return List of users with matching last name
     */
    @GetMapping("/search/lastname")
    public ResponseEntity<List<User>> searchUsersByLastName(@RequestParam String lastName) {
        List<User> users = userService.searchUsersByLastName(lastName);
        return ResponseEntity.ok(users);
    }
}
