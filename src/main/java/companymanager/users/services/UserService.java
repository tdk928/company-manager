package companymanager.users.services;

import companymanager.users.models.RegisterRequest;
import companymanager.users.models.UpdateUserRequest;
import companymanager.users.models.UserDto;
import companymanager.users.models.RoleDto;
import companymanager.users.entities.Role;
import companymanager.users.entities.User;
import companymanager.users.models.RoleRepository;
import companymanager.users.models.UserRepository;
import companymanager.exception.CustomResponseStatusException;
import companymanager.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for User business logic
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    /**
     * Register a new user
     * @param request the user registration request
     * @return the created user DTO
     */
    public UserDto registerUser(RegisterRequest request) {
        log.info("Registering new user with firstName: {}, lastName: {}, EGN: {}", 
                request.getFirstName(), request.getLastName(), request.getEgn());
        
        // Convert request to User entity using builder
        User user = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .lastName(request.getLastName())
                .egn(request.getEgn())
                .build();
        
        // Validate user data
        user.validate();
        
        // Check if EGN already exists
        if (userRepository.existsByEgn(user.getEgn())) {
            log.error("Failed to register user: EGN {} already exists", user.getEgn());
            throw new CustomResponseStatusException(HttpStatus.CONFLICT, ErrorCode.ERR009, user.getEgn());
        }
        
        // Automatically assign 'user' role to the new registered user
        Role userRole = roleRepository.findByName("user")
                .orElseThrow(() -> {
                    log.error("User role not found in the system");
                    return new CustomResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERR100);
                });
        
        // Set the user role
        user.setRole(userRole);
        
        User savedUser = userRepository.save(user);
        
        log.info("Successfully registered user with ID: {} and EGN: {}, assigned user role", savedUser.getId(), savedUser.getEgn());
        
        return convertToDto(savedUser);
    }
    
    /**
     * Get all users
     * @return List of all user DTOs
     */
    public List<UserDto> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} users", userDtos.size());
        return userDtos;
    }
    
    /**
     * Get user by ID
     * @param id the user ID
     * @return Optional containing the user DTO if found
     */
    public Optional<UserDto> getUserById(Long id) {
        log.info("Fetching user by ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("User found with ID: {}", id);
            return Optional.of(convertToDto(user.get()));
        } else {
            log.warn("User not found with ID: {}", id);
            return Optional.empty();
        }
    }
    
    /**
     * Get user by EGN
     * @param egn the EGN to search for
     * @return Optional containing the user DTO if found
     */
    public Optional<UserDto> getUserByEgn(String egn) {
        log.info("Fetching user by EGN: {}", egn);
        Optional<User> user = userRepository.findByEgn(egn);
        if (user.isPresent()) {
            log.info("User found with EGN: {}", egn);
            return Optional.of(convertToDto(user.get()));
        } else {
            log.warn("User not found with EGN: {}", egn);
            return Optional.empty();
        }
    }
    
    /**
     * Search users by first name
     * @param firstName the first name to search for
     * @return List of users with matching first name
     */
    public List<UserDto> searchUsersByFirstName(String firstName) {
        log.info("Searching users by first name: {}", firstName);
        List<User> users = userRepository.findByFirstNameContainingIgnoreCase(firstName);
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} users with first name: {}", userDtos.size(), firstName);
        return userDtos;
    }
    
    /**
     * Search users by last name
     * @param lastName the last name to search for
     * @return List of users with matching last name
     */
    public List<UserDto> searchUsersByLastName(String lastName) {
        log.info("Searching users by last name: {}", lastName);
        List<User> users = userRepository.findByLastNameContainingIgnoreCase(lastName);
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} users with last name: {}", userDtos.size(), lastName);
        return userDtos;
    }
    
    /**
     * Update an existing user
     * @param id the user ID
     * @param request the update request
     * @return the updated user DTO
     */
    public UserDto updateUser(Long id, UpdateUserRequest request) {
        log.info("Updating user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Failed to update user: User not found with ID: {}", id);
                    return new CustomResponseStatusException(HttpStatus.NOT_FOUND, ErrorCode.ERR010, id);
                });
        
        // Convert request to User entity for validation using builder
        User userDetails = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .lastName(request.getLastName())
                .egn(request.getEgn())
                .build();
        
        // Validate updated user data
        userDetails.validate();
        
        // Check if EGN is being changed and if the new EGN already exists
        if (!user.getEgn().equals(userDetails.getEgn()) && 
            userRepository.existsByEgn(userDetails.getEgn())) {
            log.error("Failed to update user: EGN {} already exists", userDetails.getEgn());
            throw new CustomResponseStatusException(HttpStatus.CONFLICT, ErrorCode.ERR009, userDetails.getEgn());
        }
        
        user.setFirstName(userDetails.getFirstName());
        user.setSecondName(userDetails.getSecondName());
        user.setLastName(userDetails.getLastName());
        user.setEgn(userDetails.getEgn());
        
        User updatedUser = userRepository.save(user);
        log.info("Successfully updated user with ID: {}", id);
        
        return convertToDto(updatedUser);
    }
    
    /**
     * Delete a user
     * @param id the user ID
     */
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Failed to delete user: User not found with ID: {}", id);
                    return new CustomResponseStatusException(HttpStatus.NOT_FOUND, ErrorCode.ERR010, id);
                });
        
        userRepository.delete(user);
        log.info("Successfully deleted user with ID: {}", id);
    }
    
    /**
     * Convert User entity to UserDto
     * @param user the user entity
     * @return UserDto
     */
    private UserDto convertToDto(User user) {
        RoleDto roleDto = null;
        if (user.getRole() != null) {
            roleDto = convertRoleToDto(user.getRole());
        }
        
        return UserDto.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .lastName(user.getLastName())
                .role(roleDto)
                .build();
    }
    
    /**
     * Convert Role entity to RoleDto
     * @param role the role entity
     * @return RoleDto
     */
    private RoleDto convertRoleToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }
}
