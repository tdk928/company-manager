package companymanager.companies.models;

import companymanager.companies.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Company entity
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    /**
     * Find company by EIK
     * @param eik the EIK to search for
     * @return Optional containing the company if found
     */
    Optional<Company> findByEik(String eik);
    
    /**
     * Check if company exists by EIK
     * @param eik the EIK to check
     * @return true if company exists, false otherwise
     */
    boolean existsByEik(String eik);
    
    /**
     * Find companies by name containing (case-insensitive)
     * @param name the name to search for
     * @return List of companies with matching names
     */
    List<Company> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find companies by address containing (case-insensitive)
     * @param address the address to search for
     * @return List of companies with matching addresses
     */
    List<Company> findByAddressContainingIgnoreCase(String address);
}
