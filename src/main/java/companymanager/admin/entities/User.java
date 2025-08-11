package companymanager.admin.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User entity representing administrative users in the system
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(max = 30, message = "First name cannot exceed 30 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Size(max = 30, message = "Second name cannot exceed 30 characters")
    @Column(name = "second_name")
    private String secondName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 30, message = "Last name cannot exceed 30 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NotBlank(message = "EGN is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "EGN must be exactly 10 digits")
    @Column(name = "egn", nullable = false, unique = true)
    private String egn;
}
