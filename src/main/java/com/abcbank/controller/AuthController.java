package com.abcbank.controller;

import com.abcbank.dto.UserAuthDTO;
import com.abcbank.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user.
     *
     * @param userAuthDTO The user credentials and role.
     * @return Success message.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        authService.register(userAuthDTO);
        return ResponseEntity.ok("User registered successfully!");
    }

    /**
     * Login a user and return a JWT token.
     *
     * @param userAuthDTO The user credentials.
     * @return The JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        String token = authService.login(userAuthDTO);
        return ResponseEntity.ok(token);
    }
}

