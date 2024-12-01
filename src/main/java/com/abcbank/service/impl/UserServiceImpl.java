package com.abcbank.service.impl;

import com.abcbank.dto.UserAuthDTO;
import com.abcbank.entity.User;
import com.abcbank.exception.UserAuthException;
import com.abcbank.repository.UserRepository;
import com.abcbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAuthDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserAuthException("User not found with username: " + username));
        return mapToDTO(user);
    }

    @Override
    public UserAuthDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserAuthException("User not found with ID: " + id));
        return mapToDTO(user);
    }

    @Override
    public UserAuthDTO updateUser(Long id, UserAuthDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserAuthException("User not found with ID: " + id));

        // Update user details
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole().toUpperCase());

        return this.mapToDTO(userRepository.save(user));

    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserAuthException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserAuthDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserAuthDTO mapToDTO(User user) {
        return new UserAuthDTO(user.getUsername(), user.getPassword(), user.getRole());
    }
}
