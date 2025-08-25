package com.airtel.loginsignup.controller;

import org.springframework.web.bind.annotation.*;
import com.airtel.loginsignup.model.User;
import com.airtel.loginsignup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // --- SIGNUP API ---
    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (userRepository.findByEmail(user.getEmail()) != null) {
            response.put("message", "❌ Email already exists!");
            return response;
        }

        userRepository.save(user);

        response.put("message", "✅ Registered successfully!");
        response.put("username", user.getUsername()); // ✅ Now this will work
        return response;
    }

    // --- LOGIN API ---
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            response.put("message", "✅ Login successful!");
            response.put("username", existingUser.getUsername());
        } else {
            response.put("message", "❌ Invalid email or password!");
        }

        return response;
    }
}
