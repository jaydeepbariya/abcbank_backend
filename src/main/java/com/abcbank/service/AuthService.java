package com.abcbank.service;


import com.abcbank.dto.UserAuthDTO;

public interface AuthService {

    void register(UserAuthDTO userAuthDTO);
    String login(UserAuthDTO userAuthDTO);
}
