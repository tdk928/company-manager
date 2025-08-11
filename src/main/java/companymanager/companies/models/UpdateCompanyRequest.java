package companymanager.companies.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for company update requests
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompanyRequest {
    
    private String name;
    private String eik;
    private String address;
}
