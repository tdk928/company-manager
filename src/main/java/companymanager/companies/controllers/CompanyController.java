package companymanager.companies.controllers;

import companymanager.companies.models.CreateCompanyRequest;
import companymanager.companies.models.CompanyDto;
import companymanager.companies.services.CompanyService;
import companymanager.exception.CustomResponseStatusException;
import companymanager.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for company management
 */
@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {
    
    private final CompanyService companyService;
    
    /**
     * Create a new company
     * @param request the company creation request
     * @return Created company
     */
    @PostMapping("/register")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CreateCompanyRequest request) {
        try {
            CompanyDto createdCompany = companyService.createCompany(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to create company"
            );
        }
    }
}
