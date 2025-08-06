package companymanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    
    @GetMapping("/")
    public String home() {
        return "Welcome to Company Manager API! Use /api/companies to access the companies endpoint.";
    }
    
    @GetMapping("/health")
    public String health() {
        return "Application is running successfully!";
    }
} 