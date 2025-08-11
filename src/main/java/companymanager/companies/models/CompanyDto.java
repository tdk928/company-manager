package companymanager.companies.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for company display (response)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    
    private Long id;
    private String name;
    private String eik;
    private String address;
    private String email;
    private String phone;
    private LocalDate validFrom;
    private LocalDate validTo;
}
