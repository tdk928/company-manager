package companymanager.admin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for User display purposes (without sensitive data like EGN)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    
    private String firstName;
    private String secondName;
    private String lastName;
    private RoleDto role;
}
