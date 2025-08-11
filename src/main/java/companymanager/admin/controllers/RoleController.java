package companymanager.admin.controllers;

import companymanager.admin.models.RoleDto;
import companymanager.admin.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Role management operations
 */
@RestController
@RequestMapping("/api/admin/roles")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RoleController {
    
    private final RoleService roleService;
    
    /**
     * GET - Retrieve all roles
     * @return List of all roles
     */
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
    
    /**
     * GET - Retrieve role by ID
     * @param id the role ID
     * @return Role if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        Optional<RoleDto> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * GET - Retrieve role by name
     * @param name the role name
     * @return Role if found, 404 if not found
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDto> getRoleByName(@PathVariable String name) {
        Optional<RoleDto> role = roleService.getRoleByName(name);
        return role.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
