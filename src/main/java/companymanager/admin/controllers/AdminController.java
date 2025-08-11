package companymanager.admin.controllers;

import companymanager.admin.models.CreateUserRequest;
import companymanager.admin.models.UpdateUserRequest;
import companymanager.admin.models.UserDto;
import companymanager.admin.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for User management operations
 */
@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AdminController {
    
    private final UserService userService;
    
    /**
     * GET - Retrieve all users
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    /**
     * GET - Retrieve user by ID
     * @param id the user ID
     * @return User if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * GET - Retrieve user by EGN
     * @param egn the EGN to search for
     * @return User if found, 404 if not found
     */
    @GetMapping("/egn/{egn}")
    public ResponseEntity<UserDto> getUserByEgn(@PathVariable String egn) {
        Optional<UserDto> user = userService.getUserByEgn(egn);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST - Create a new user
     * @param request the user creation request
     * @return Created user with 201 status
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        UserDto createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    /**
     * PUT - Update an existing user
     * @param id the user ID
     * @param request the update request
     * @return Updated user if successful, 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        UserDto updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }
    
    /**
     * DELETE - Delete a user
     * @param id the user ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * GET - Search users by first name
     * @param firstName the first name to search for
     * @return List of users with matching first name
     */
    @GetMapping("/search/firstname")
    public ResponseEntity<List<UserDto>> searchUsersByFirstName(@RequestParam String firstName) {
        List<UserDto> users = userService.searchUsersByFirstName(firstName);
        return ResponseEntity.ok(users);
    }
    
    /**
     * GET - Search users by last name
     * @param lastName the last name to search for
     * @return List of users with matching last name
     */
    @GetMapping("/search/lastname")
    public ResponseEntity<List<UserDto>> searchUsersByLastName(@RequestParam String lastName) {
        List<UserDto> users = userService.searchUsersByLastName(lastName);
        return ResponseEntity.ok(users);
    }
}
