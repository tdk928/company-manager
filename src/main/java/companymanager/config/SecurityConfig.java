package companymanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for password encoding and endpoint access
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * Bean for password encoding using BCrypt with enhanced security
     * @return BCryptPasswordEncoder instance with strength 12
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt strength 12 provides excellent security (default is 10)
        // Higher strength = more secure but slower hashing
        // 12 is a good balance between security and performance
        return new BCryptPasswordEncoder(12);
    }
    
    /**
     * Security filter chain configuration
     * @param http HttpSecurity object
     * @return SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> {})
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.deny()) // Prevent clickjacking
                .contentTypeOptions(contentType -> {}) // Prevent MIME type sniffing
            )
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(AbstractHttpConfigurer::disable) // Disable basic auth
            .formLogin(AbstractHttpConfigurer::disable); // Disable form login
        
        return http.build();
    }
    
    /**
     * CORS configuration bean for modern cross-origin handling
     * @return CorsConfigurationSource
     */
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOriginPatterns(java.util.List.of("*")); // Allow all origins
        configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(java.util.List.of("*"));
        configuration.setAllowCredentials(false); // Set to true if you need credentials
        
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
