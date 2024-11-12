package com.edutecno.backend.service;

import com.edutecno.backend.dto.SignInRequestDto;
import com.edutecno.backend.dto.UserRegisterDto;
import com.edutecno.backend.model.User;

public interface UserService {

    String signIn(SignInRequestDto signInRequestDto);

    User signUp(UserRegisterDto userRegisterDto);

    User loadUserByUsername(String username);
}
