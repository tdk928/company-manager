package companymanager.companies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import companymanager.exception.CustomResponseStatusException;
import companymanager.exception.ErrorCode;

import java.time.LocalDate;

/**
 * Entity representing a company in the system
 */
@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    @Column(name = "eik", nullable = false, length = 9, unique = true)
    private String eik;
    
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;
    
    @Column(name = "valid_to")
    private LocalDate validTo;
    
    /**
     * Pre-persist hook to set validFrom to current date when creating
     */
    @PrePersist
    protected void onCreate() {
        if (validFrom == null) {
            validFrom = LocalDate.now();
        }
    }
    
    /**
     * Validate company data
     * @throws CustomResponseStatusException if validation fails
     */
    public void validate() {
        validateName();
        validateEik();
        validateAddress();
        validateEmail();
        validatePhone();
    }
    
    /**
     * Validate company name
     * @throws CustomResponseStatusException if name is invalid
     */
    private void validateName() {
        if (name == null || name.trim().isEmpty()) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR021,
                "Company name is required"
            );
        }
        if (name.length() > 50) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR022,
                "Company name cannot exceed 50 characters"
            );
        }
    }
    
    /**
     * Validate EIK (Bulgarian company identifier)
     * @throws CustomResponseStatusException if EIK is invalid
     */
    private void validateEik() {
        if (eik == null || eik.trim().isEmpty()) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR023,
                "EIK is required"
            );
        }
        if (!eik.matches("\\d{9}")) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR024,
                "EIK must be exactly 9 digits"
            );
        }
    }
    
    /**
     * Validate company address
     * @throws CustomResponseStatusException if address is invalid
     */
    private void validateAddress() {
        if (address == null || address.trim().isEmpty()) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR025,
                "Company address is required"
            );
        }
        if (address.length() > 50) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR026,
                "Company address cannot exceed 50 characters"
            );
        }
    }
    
    /**
     * Validate company email
     * @throws CustomResponseStatusException if email is invalid
     */
    private void validateEmail() {
        if (email == null || email.trim().isEmpty()) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR030,
                "Company email is required"
            );
        }
        // Email regex pattern for validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR031,
                "Company email format is invalid"
            );
        }
    }
    
    /**
     * Validate company phone
     * @throws CustomResponseStatusException if phone is invalid
     */
    private void validatePhone() {
        if (phone == null || phone.trim().isEmpty()) {
            throw new CustomResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ErrorCode.ERR032,
                "Company phone is required"
            );
        }
    }
}
