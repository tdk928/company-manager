package companymanager.companies.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for company creation requests
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCompanyRequest {
    
    private String name;
    private String eik;
    private String address;
    private String email;
    private String phone;
}
