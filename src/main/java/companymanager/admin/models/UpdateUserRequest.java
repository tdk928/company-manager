package companymanager.admin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating existing users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    
    private String firstName;
    private String secondName;
    private String lastName;
    private String egn;
}
