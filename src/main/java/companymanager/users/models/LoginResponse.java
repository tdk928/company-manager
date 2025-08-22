package companymanager.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for login response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    
    private String message;
    private UserDto user;
    private String token; // For future JWT implementation
}
