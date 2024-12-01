package com.abcbank.service.impl;


import com.abcbank.dto.UserAuthDTO;
import com.abcbank.entity.User;
import com.abcbank.exception.AuthException;
import com.abcbank.repository.UserRepository;
import com.abcbank.security.JwtTokenProvider;
import com.abcbank.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void register(UserAuthDTO userAuthDTO) {

        if (userRepository.existsByUsername(userAuthDTO.getUsername())) {
            throw new AuthException("Username already exists!");
        }

        User user = new User();
        user.setUsername(userAuthDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userAuthDTO.getPassword())); // Hash password
        user.setRole(userAuthDTO.getRole().toUpperCase()); // Ensure roles are standardized

        userRepository.save(user);
    }

    @Override
    public String login(UserAuthDTO userAuthDTO) {

        User user = userRepository.findByUsername(userAuthDTO.getUsername())
                .orElseThrow(() -> new AuthException("Invalid username or password!"));

        if (!passwordEncoder.matches(userAuthDTO.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid username or password!");
        }

        return jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
    }
}
