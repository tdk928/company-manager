package companymanager.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for User display purposes (without sensitive data like EGN)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private String firstName;
    private String secondName;
    private String lastName;
}
