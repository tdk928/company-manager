package companymanager.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for login (users with EGN, companies with EIK)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    
    private String egnOrEik; // For user login (EGN) or company login (EIK) - must be exactly 10 characters
    private String password;
}
