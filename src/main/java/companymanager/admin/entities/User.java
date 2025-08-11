package companymanager.admin.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * User entity representing administrative users in the system
 */
@Entity
@Table(name = "users")
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
    
    // Default constructor
    public User() {}
    
    // Constructor with all fields
    public User(String firstName, String secondName, String lastName, String egn) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.egn = egn;
    }
    
    // Constructor with all fields including id
    public User(Long id, String firstName, String secondName, String lastName, String egn) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.egn = egn;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSecondName() {
        return secondName;
    }
    
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEgn() {
        return egn;
    }
    
    public void setEgn(String egn) {
        this.egn = egn;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", egn='" + egn + '\'' +
                '}';
    }
}
