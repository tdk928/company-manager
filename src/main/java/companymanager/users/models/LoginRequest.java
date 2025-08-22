package companymanager.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user login
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    
    private String egn;
    private String password;
}
