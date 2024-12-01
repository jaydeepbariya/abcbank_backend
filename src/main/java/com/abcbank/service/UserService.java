package com.abcbank.service;


import com.abcbank.dto.UserAuthDTO;

import java.util.List;

public interface UserService {

    UserAuthDTO getUserByUsername(String username);

    UserAuthDTO getUserById(Long id);

    UserAuthDTO updateUser(Long id, UserAuthDTO userDTO);

    void deleteUser(Long id);

    List<UserAuthDTO> getAllUsers();
}
