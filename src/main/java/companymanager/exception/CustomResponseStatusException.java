package companymanager.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling application-specific errors
 * with HTTP status codes and error codes
 */
public class CustomResponseStatusException extends RuntimeException {
    
    private final HttpStatus status;
    private final ErrorCode errorCode;
    private final String message;
    
    /**
     * Constructor with status, error code, and message
     * @param status HTTP status code
     * @param errorCode application error code
     * @param message error message
     */
    public CustomResponseStatusException(HttpStatus status, ErrorCode errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
    
    /**
     * Constructor with status and error code (uses default message from error code)
     * @param status HTTP status code
     * @param errorCode application error code
     */
    public CustomResponseStatusException(HttpStatus status, ErrorCode errorCode) {
        this(status, errorCode, errorCode.getMessage());
    }
    
    /**
     * Constructor with status, error code, and formatted message with parameters
     * @param status HTTP status code
     * @param errorCode application error code
     * @param params parameters for message formatting
     */
    public CustomResponseStatusException(HttpStatus status, ErrorCode errorCode, Object... params) {
        this(status, errorCode, errorCode.getFormattedMessage(params));
    }
    
    /**
     * Get HTTP status
     * @return HTTP status
     */
    public HttpStatus getStatus() {
        return status;
    }
    
    /**
     * Get error code
     * @return error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    /**
     * Get error message
     * @return error message
     */
    @Override
    public String getMessage() {
        return message;
    }
    
    /**
     * Get HTTP status value
     * @return HTTP status value
     */
    public int getStatusCode() {
        return status.value();
    }
}
