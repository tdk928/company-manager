package companymanager.users.controllers;

import companymanager.users.models.RegisterRequest;
import companymanager.users.models.LoginRequest;
import companymanager.users.models.LoginResponse;
import companymanager.users.models.UserDto;
import companymanager.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User registration
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
     * POST - Login user
     * @param request the login request
     * @return LoginResponse with user details and token
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = userService.loginUser(request);
        return ResponseEntity.ok(loginResponse);
    }
}
