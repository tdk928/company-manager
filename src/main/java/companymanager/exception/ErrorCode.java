package companymanager.exception;

/**
 * Enum containing all error codes and their corresponding messages
 */
public enum ErrorCode {
    
    // User validation errors
    ERR001("First name cannot exceed 30 characters"),
    ERR002("First name is required"),
    ERR003("Second name cannot exceed 30 characters"),
    ERR004("Last name cannot exceed 30 characters"),
    ERR005("Last name is required"),
    ERR006("EGN is required"),
    ERR007("EGN must be exactly 10 digits"),
    ERR008("EGN must contain only numeric characters"),
    
    // User business logic errors
    ERR009("User with EGN {0} already exists"),
    ERR010("User not found with id: {0}"),
    ERR011("User with EGN {0} not found"),
    
    // Company validation errors
    ERR012("Company name is required"),
    ERR013("Company name cannot exceed 50 characters"),
    ERR014("EIK is required"),
    ERR015("EIK must be exactly 9 digits"),
    ERR016("Company address is required"),
    ERR017("Company address cannot exceed 50 characters"),
    
    // Company business logic errors
    ERR018("Company with EIK {0} already exists"),
    ERR019("Company not found with id: {0}"),
    ERR020("Company with EIK {0} not found"),
    
    // Generic errors
    ERR100("Internal server error"),
    ERR101("Invalid request data"),
    ERR102("Resource not found"),
    ERR103("Validation failed"),
    ERR104("Unauthorized access"),
    ERR105("Forbidden operation");
    
    private final String message;
    
    ErrorCode(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    /**
     * Get formatted message with parameters
     * @param params parameters to replace {0}, {1}, etc. placeholders
     * @return formatted message
     */
    public String getFormattedMessage(Object... params) {
        String formattedMessage = this.message;
        for (int i = 0; i < params.length; i++) {
            formattedMessage = formattedMessage.replace("{" + i + "}", String.valueOf(params[i]));
        }
        return formattedMessage;
    }
}
