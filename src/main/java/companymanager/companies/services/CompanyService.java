package companymanager.companies.services;

import companymanager.companies.models.CreateCompanyRequest;
import companymanager.companies.models.UpdateCompanyRequest;
import companymanager.companies.models.CompanyDto;
import companymanager.companies.entities.Company;
import companymanager.companies.models.CompanyRepository;
import companymanager.exception.CustomResponseStatusException;
import companymanager.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for Company business logic
 */
@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    
    /**
     * Get all companies
     * @return List of all company DTOs
     */
    public List<CompanyDto> getAllCompanies() {
        log.info("Fetching all companies");
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} companies", companyDtos.size());
        return companyDtos;
    }
    
    /**
     * Get company by ID
     * @param id the company ID
     * @return Optional containing the company DTO if found
     */
    public Optional<CompanyDto> getCompanyById(Long id) {
        log.info("Fetching company by ID: {}", id);
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            log.info("Company found with ID: {}", id);
            return Optional.of(convertToDto(company.get()));
        } else {
            log.warn("Company not found with ID: {}", id);
            return Optional.empty();
        }
    }
    
    /**
     * Get company by EIK
     * @param eik the EIK to search for
     * @return Optional containing the company DTO if found
     */
    public Optional<CompanyDto> getCompanyByEik(String eik) {
        log.info("Fetching company by EIK: {}", eik);
        Optional<Company> company = companyRepository.findByEik(eik);
        if (company.isPresent()) {
            log.info("Company found with EIK: {}", eik);
            return Optional.of(convertToDto(company.get()));
        } else {
            log.warn("Company not found with EIK: {}", eik);
            return Optional.empty();
        }
    }
    
    /**
     * Create a new company
     * @param request the company creation request
     * @return the created company DTO
     */
    public CompanyDto createCompany(CreateCompanyRequest request) {
        log.info("Creating new company with name: {}, EIK: {}", 
                request.getName(), request.getEik());
        
        // Convert request to Company entity using builder
        Company company = Company.builder()
                .name(request.getName())
                .eik(request.getEik())
                .address(request.getAddress())
                .build();
        
        // Validate company data
        company.validate();
        
        // Check if EIK already exists
        if (companyRepository.existsByEik(company.getEik())) {
            log.error("Company with EIK {} already exists", company.getEik());
            throw new CustomResponseStatusException(
                HttpStatus.CONFLICT,
                ErrorCode.ERR018,
                ErrorCode.ERR018.getFormattedMessage(company.getEik())
            );
        }
        
        // Save company
        Company savedCompany = companyRepository.save(company);
        log.info("Company created successfully with ID: {}", savedCompany.getId());
        
        return convertToDto(savedCompany);
    }
    
    /**
     * Update an existing company
     * @param id the company ID
     * @param request the company update request
     * @return the updated company DTO
     */
    public CompanyDto updateCompany(Long id, UpdateCompanyRequest request) {
        log.info("Updating company with ID: {}", id);
        
        // Find existing company
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Company not found with ID: {}", id);
                    throw new CustomResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        ErrorCode.ERR019,
                        ErrorCode.ERR019.getFormattedMessage(id)
                    );
                });
        
        // Convert request to Company entity for validation
        Company companyDetails = Company.builder()
                .name(request.getName())
                .eik(request.getEik())
                .address(request.getAddress())
                .build();
        
        // Validate updated company data
        companyDetails.validate();
        
        // Check if EIK is being changed and if new EIK already exists
        if (!existingCompany.getEik().equals(companyDetails.getEik()) &&
            companyRepository.existsByEik(companyDetails.getEik())) {
            log.error("Company with EIK {} already exists", companyDetails.getEik());
            throw new CustomResponseStatusException(
                HttpStatus.CONFLICT,
                ErrorCode.ERR018,
                ErrorCode.ERR018.getFormattedMessage(companyDetails.getEik())
            );
        }
        
        // Update company fields
        existingCompany.setName(companyDetails.getName());
        existingCompany.setEik(companyDetails.getEik());
        existingCompany.setAddress(companyDetails.getAddress());
        
        // Save updated company
        Company updatedCompany = companyRepository.save(existingCompany);
        log.info("Company updated successfully with ID: {}", updatedCompany.getId());
        
        return convertToDto(updatedCompany);
    }
    
    /**
     * Delete a company
     * @param id the company ID
     */
    public void deleteCompany(Long id) {
        log.info("Deleting company with ID: {}", id);
        
        if (!companyRepository.existsById(id)) {
            log.error("Company not found with ID: {}", id);
            throw new CustomResponseStatusException(
                HttpStatus.NOT_FOUND,
                ErrorCode.ERR019,
                ErrorCode.ERR019.getFormattedMessage(id)
            );
        }
        
        companyRepository.deleteById(id);
        log.info("Company deleted successfully with ID: {}", id);
    }
    
    /**
     * Search companies by name
     * @param name the name to search for
     * @return List of companies with matching names
     */
    public List<CompanyDto> searchCompaniesByName(String name) {
        log.info("Searching companies by name: {}", name);
        List<Company> companies = companyRepository.findByNameContainingIgnoreCase(name);
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} companies matching name: {}", companyDtos.size(), name);
        return companyDtos;
    }
    
    /**
     * Search companies by address
     * @param address the address to search for
     * @return List of companies with matching addresses
     */
    public List<CompanyDto> searchCompaniesByAddress(String address) {
        log.info("Searching companies by address: {}", address);
        List<Company> companies = companyRepository.findByAddressContainingIgnoreCase(address);
        List<CompanyDto> companyDtos = companies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        log.info("Found {} companies matching address: {}", companyDtos.size(), address);
        return companyDtos;
    }
    
    /**
     * Convert Company entity to CompanyDto
     * @param company the company entity
     * @return CompanyDto
     */
    private CompanyDto convertToDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .eik(company.getEik())
                .address(company.getAddress())
                .validFrom(company.getValidFrom())
                .validTo(company.getValidTo())
                .build();
    }
}
