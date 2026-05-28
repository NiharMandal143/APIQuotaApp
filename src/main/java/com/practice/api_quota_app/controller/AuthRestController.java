package com.practice.api_quota_app.controller;


import com.practice.api_quota_app.dto.LoginRequest;
import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        User user = userService.login(
                request.getUsername(),
                request.getPassword()
        );

        if(user != null) {

            return ResponseEntity.ok(user);
        }

        return ResponseEntity
                .status(401)
                .body("Invalid Credentials");
    }
}