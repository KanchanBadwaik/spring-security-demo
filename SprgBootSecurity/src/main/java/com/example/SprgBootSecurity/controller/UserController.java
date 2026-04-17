package com.example.SprgBootSecurity.controller;

import com.example.SprgBootSecurity.entity.DemoUser;
import com.example.SprgBootSecurity.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller class for handling user-related HTTP requests.
 * Provides endpoints for user registration and accessing different home pages.
 */
@Controller
public class UserController {

    // Injects the DemoUserService to handle user-related operations.
    @Autowired
    DemoUserService service;

    /**
     * Endpoint to register a new user.
     *
     * @param user the user details sent in the request body
     * @return a ResponseEntity with a success message and HTTP status
     */
    @PostMapping("/registerUser")
    public ResponseEntity<?> saveUSer(@RequestBody DemoUser user){
        System.out.println("Inside RegisterUser method");
        service.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Save Successfully");
    }

    /**
     * Endpoint to access the public home page.
     *
     * @return a ResponseEntity with the home page message and HTTP status
     */
    @GetMapping("/home")
    public ResponseEntity<?> homePage(){
        return  ResponseEntity.status(HttpStatus.OK).body("HOME");
    }

    /**
     * Endpoint to access the user-specific home page.
     * Accessible only to users with the USER or ADMIN role.
     *
     * @return a ResponseEntity with the user home page message and HTTP status
     */
    @GetMapping("/user/home")
    public ResponseEntity<?>  userHome(){
        return ResponseEntity.status(HttpStatus.OK).body("USER HOME");
    }

    /**
     * Endpoint to access the admin-specific home page.
     * Accessible only to users with the ADMIN role.
     *
     * @return a ResponseEntity with the admin home page message and HTTP status
     */
    @GetMapping("/admin/home")
    public ResponseEntity<?>  adminHome(){
        return ResponseEntity.status(HttpStatus.OK).body("ADMIN HOME");
    }
}