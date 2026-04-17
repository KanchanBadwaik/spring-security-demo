package com.example.SprgBootSecurity.config;

import com.example.SprgBootSecurity.service.DemoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the Spring Boot application.
 * Configures authentication, authorization, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Injects the custom implementation of UserDetailsService.
    @Autowired
    DemoUserDetailsService userDetailsService;

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param httpSecurity the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        return httpSecurity.csrf(csrf -> csrf.disable()) // Disables CSRF protection.
                .authorizeHttpRequests(registry -> {
                    // Restricts access to endpoints based on roles.
                    registry.requestMatchers("/admin/**").hasRole("ADMIN"); // Only accessible to ADMIN role.
                    registry.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN"); // Accessible to USER and ADMIN roles.
                    registry.requestMatchers("/registerUser", "/home").permitAll(); // Publicly accessible endpoints.
                })
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer.permitAll()) // Enables form-based login for all users.
                .build();
    }

    /**
     * Provides the UserDetailsService bean.
     *
     * @return the custom UserDetailsService implementation
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /**
     * Provides the PasswordEncoder bean.
     *
     * @return a BCryptPasswordEncoder instance for password hashing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication provider.
     *
     * @return a DaoAuthenticationProvider configured with the UserDetailsService and PasswordEncoder
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}