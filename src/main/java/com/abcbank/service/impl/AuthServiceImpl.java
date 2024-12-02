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

import javax.swing.text.html.Option;
import java.util.Optional;

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

        User user = new User();
        user.setEmail(userAuthDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
        user.setRole(userAuthDTO.getRole().toUpperCase());

        userRepository.save(user);
    }

    @Override
    public String login(UserAuthDTO userAuthDTO) {

        return jwtTokenProvider.generateToken(userAuthDTO.getEmail());
    }
}
