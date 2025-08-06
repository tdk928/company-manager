package companymanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple REST controller to demonstrate the application is running
 */
@RestController
public class HomeController {

    /**
     * Home endpoint that returns a welcome message
     * @return welcome message
     */
    @GetMapping("/")
    public String home() {
        return "Welcome to Company Manager Application!";
    }

    /**
     * Health check endpoint
     * @return health status
     */
    @GetMapping("/health")
    public String health() {
        return "Application is running successfully!";
    }
} 