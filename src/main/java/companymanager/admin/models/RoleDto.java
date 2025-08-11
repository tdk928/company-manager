package companymanager.admin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Role display purposes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    
    private Long id;
    private String name;
    private String description;
}
