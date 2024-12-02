package com.abcbank.controller;

import com.abcbank.dto.UserAuthDTO;
import com.abcbank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get a user by ID.
     *
     * @param id The ID of the user.
     * @return User details as UserAuthDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserAuthDTO> getUserById(@PathVariable Long id) {
        UserAuthDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Update a user's details.
     *
     * @param id The ID of the user to update.
     * @param userDTO The updated user details.
     * @return The updated UserAuthDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserAuthDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserAuthDTO userDTO) {
        UserAuthDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return Success message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    /**
     * Get all users.
     *
     * @return List of all users as UserAuthDTOs.
     */
    @GetMapping
    public ResponseEntity<List<UserAuthDTO>> getAllUsers() {
        List<UserAuthDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

