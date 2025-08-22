package companymanager.users.controllers;

import companymanager.users.models.RegisterRequest;
import companymanager.users.models.LoginRequest;
import companymanager.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User registration
 */
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    /**
     * POST - Register a new user
     * @param request the user registration request
     * @return true if successful, false otherwise
     */
    @PostMapping("/register")
    public boolean registerUser(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }
    
    /**
     * POST - Login user
     * @param request the login request
     * @return true if login successful, false otherwise
     */
    @PostMapping("/login")
    public boolean loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }
}
