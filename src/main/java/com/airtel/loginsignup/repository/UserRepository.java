package com.airtel.loginsignup.repository;

import com.airtel.loginsignup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface will automatically give you database methods
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find user by email
    User findByEmail(String email);
}
