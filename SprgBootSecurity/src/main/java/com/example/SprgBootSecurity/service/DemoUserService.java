package com.example.SprgBootSecurity.service;

import com.example.SprgBootSecurity.entity.DemoUser;
import com.example.SprgBootSecurity.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations.
 * Provides functionality to save users with encoded passwords.
 */
@Service
public class DemoUserService {

    // Injects the DemoRepository to interact with the database.
    @Autowired
    DemoRepository repo;

    // Injects the PasswordEncoder to encode user passwords.
    @Autowired
    PasswordEncoder encoder;

    /**
     * Saves a user to the database after encoding their password.
     *
     * @param user the DemoUser entity containing user details
     * @return the saved DemoUser entity
     */
    public DemoUser saveUser(DemoUser user){
        System.out.println("Inside Service class ");
        user.setPassword(encoder.encode(user.getPassword())); // Encodes the user's password.
        DemoUser savedUser = repo.save(user); // Saves the user to the database.
        return savedUser;
    }
}