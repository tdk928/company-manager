package com.company.companymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Company Manager Spring Boot Application
 * This class contains the main method that starts the Spring Boot application
 */
@SpringBootApplication
public class CompanyManager {

    /**
     * Main method that starts the Spring Boot application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CompanyManager.class, args);
    }
} 