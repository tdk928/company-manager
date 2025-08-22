package companymanager.users.services;

import companymanager.users.models.RoleDto;
import companymanager.users.entities.Role;
import companymanager.users.models.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for Role business logic
 */
@Service
@AllArgsConstructor
@Slf4j
public class RoleService {
    
    private final RoleRepository roleRepository;
    
    /**
     * Get all roles
     * @return List of all role DTOs
     */
    public List<RoleDto> getAllRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = roles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} roles", roleDtos.size());
        return roleDtos;
    }
    
    /**
     * Get role by ID
     * @param id the role ID
     * @return Optional containing the role DTO if found
     */
    public Optional<RoleDto> getRoleById(Long id) {
        log.info("Fetching role by ID: {}", id);
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            log.info("Role found with ID: {}", id);
            return Optional.of(convertToDto(role.get()));
        } else {
            log.warn("Role not found with ID: {}", id);
            return Optional.empty();
        }
    }
    
    /**
     * Get role by name
     * @param name the role name
     * @return Optional containing the role DTO if found
     */
    public Optional<RoleDto> getRoleByName(String name) {
        log.info("Fetching role by name: {}", name);
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            log.info("Role found with name: {}", name);
            return Optional.of(convertToDto(role.get()));
        } else {
            log.warn("Role not found with name: {}", name);
            return Optional.empty();
        }
    }
    
    /**
     * Convert Role entity to RoleDto
     * @param role the role entity
     * @return RoleDto
     */
    private RoleDto convertToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }
}
