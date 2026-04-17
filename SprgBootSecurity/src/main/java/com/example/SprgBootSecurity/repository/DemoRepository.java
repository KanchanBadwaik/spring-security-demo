package com.example.SprgBootSecurity.repository;

import com.example.SprgBootSecurity.entity.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the DemoUser entity.
 * Extends JpaRepository to provide standard database operations.
 */
@Repository
public interface DemoRepository extends JpaRepository<DemoUser, Long> {

    /**
     * Finds a DemoUser by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the found DemoUser, or empty if not found
     */
    Optional<DemoUser> findByUsername(String username);
}