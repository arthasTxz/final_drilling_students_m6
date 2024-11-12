package com.edutecno.backend.controller;

import com.edutecno.backend.dto.LoginDto;
import com.edutecno.backend.dto.SignInRequestDto;
import com.edutecno.backend.dto.UserRegisterDto;
import com.edutecno.backend.model.User;
import com.edutecno.backend.service.UserService;
import com.edutecno.backend.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok(userService.signIn(signInRequestDto));
    }

    @GetMapping
    public ResponseEntity<String> sigOut(){

        return ResponseEntity.ok("Todo bien");
    }

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.signUp(userRegisterDto));
    }
}
