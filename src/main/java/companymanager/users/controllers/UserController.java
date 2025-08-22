package companymanager.users.controllers;

import companymanager.users.models.RegisterRequest;
import companymanager.users.models.UserDto;
import companymanager.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for User operations
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    /**
     * POST - Register a new user
     * @param request the user registration request
     * @return Created user with 201 status
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterRequest request) {
        UserDto createdUser = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
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
