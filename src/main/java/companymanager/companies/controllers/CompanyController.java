package companymanager.companies.controllers;

import companymanager.companies.models.CreateCompanyRequest;
import companymanager.companies.models.UpdateCompanyRequest;
import companymanager.companies.models.CompanyDto;
import companymanager.companies.services.CompanyService;
import companymanager.exception.CustomResponseStatusException;
import companymanager.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for company management
 */
@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {
    
    private final CompanyService companyService;
    
    /**
     * Get all companies
     * @return List of all companies
     */
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        try {
            List<CompanyDto> companies = companyService.getAllCompanies();
            return ResponseEntity.ok(companies);
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to retrieve companies"
            );
        }
    }
    
    /**
     * Get company by ID
     * @param id the company ID
     * @return Company if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        try {
            Optional<CompanyDto> company = companyService.getCompanyById(id);
            if (company.isPresent()) {
                return ResponseEntity.ok(company.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to retrieve company"
            );
        }
    }
    
    /**
     * Get company by EIK
     * @param eik the EIK to search for
     * @return Company if found
     */
    @GetMapping("/eik/{eik}")
    public ResponseEntity<CompanyDto> getCompanyByEik(@PathVariable String eik) {
        try {
            Optional<CompanyDto> company = companyService.getCompanyByEik(eik);
            if (company.isPresent()) {
                return ResponseEntity.ok(company.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to retrieve company by EIK"
            );
        }
    }
    
    /**
     * Create a new company
     * @param request the company creation request
     * @return Created company
     */
    @PostMapping
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
    
    /**
     * Update an existing company
     * @param id the company ID
     * @param request the company update request
     * @return Updated company
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody UpdateCompanyRequest request) {
        try {
            CompanyDto updatedCompany = companyService.updateCompany(id, request);
            return ResponseEntity.ok(updatedCompany);
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to update company"
            );
        }
    }
    
    /**
     * Delete a company
     * @param id the company ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to delete company"
            );
        }
    }
    
    /**
     * Search companies by name
     * @param name the name to search for
     * @return List of companies with matching names
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<CompanyDto>> searchCompaniesByName(@RequestParam String name) {
        try {
            List<CompanyDto> companies = companyService.searchCompaniesByName(name);
            return ResponseEntity.ok(companies);
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to search companies by name"
            );
        }
    }
    
    /**
     * Search companies by address
     * @param address the address to search for
     * @return List of companies with matching addresses
     */
    @GetMapping("/search/address")
    public ResponseEntity<List<CompanyDto>> searchCompaniesByAddress(@RequestParam String address) {
        try {
            List<CompanyDto> companies = companyService.searchCompaniesByAddress(address);
            return ResponseEntity.ok(companies);
        } catch (CustomResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.ERR100,
                "Failed to search companies by address"
            );
        }
    }
}
