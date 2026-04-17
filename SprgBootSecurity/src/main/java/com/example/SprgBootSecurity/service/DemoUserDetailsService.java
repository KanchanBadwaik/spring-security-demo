package com.example.SprgBootSecurity.service;

import com.example.SprgBootSecurity.entity.DemoUser;
import com.example.SprgBootSecurity.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;

/**
 * Service class for managing user authentication and roles.
 * Implements the UserDetailsService interface to provide custom user details.
 */
@Service
public class DemoUserDetailsService implements UserDetailsService {

    // Injects the DemoRepository to interact with the database.
    @Autowired
    DemoRepository repo;

    /**
     * Loads a user by their username.
     *
     * @param username the username of the user to load
     * @return the UserDetails object containing user information
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getRoles(user))
                .build();
    }

    /**
     * Retrieves the roles of a user.
     * If no roles are defined, defaults to "USER".
     *
     * @param user the DemoUser entity
     * @return an array of roles assigned to the user
     */
    public String[] getRoles(DemoUser user) {
        if (user.getRole() == null)
            return new String[]{"USER"};
        else {
            return Arrays.stream(user.getRole().split(","))
                    .map(String::trim)
                    .toArray(String[]::new);
        }
    }

    /**
     * Main method for testing string splitting and role processing.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String str = "USER,ADMIN";
        String[] arr = str.split(",");
        for (String i : arr)
            System.out.println("Length of string is : " + i.length());
    }
}