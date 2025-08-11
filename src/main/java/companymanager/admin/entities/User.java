package companymanager.admin.entities;

import companymanager.exception.CustomResponseStatusException;
import companymanager.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * User entity representing administrative users in the system
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;
    
    @Column(name = "second_name")
    @JsonProperty("secondName")
    private String secondName;
    
    @Column(name = "last_name", nullable = false)
    @JsonProperty("lastName")
    private String lastName;
    
    @Column(name = "egn", nullable = false, unique = true)
    @JsonProperty("egn")
    private String egn;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    
    /**
     * Validate the user entity and throw custom exceptions if validation fails
     */
    public void validate() {
        validateFirstName();
        validateSecondName();
        validateLastName();
        validateEgn();
    }
    
    private void validateFirstName() {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR002);
        }
        if (firstName.length() > 30) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR001);
        }
    }
    
    private void validateSecondName() {
        if (secondName != null && secondName.length() > 30) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR003);
        }
    }
    
    private void validateLastName() {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR005);
        }
        if (lastName.length() > 30) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR004);
        }
    }
    
    private void validateEgn() {
        if (egn == null || egn.trim().isEmpty()) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR006);
        }
        if (egn.length() != 10) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR007);
        }
        if (!egn.matches("^[0-9]+$")) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCode.ERR008);
        }
    }
}
