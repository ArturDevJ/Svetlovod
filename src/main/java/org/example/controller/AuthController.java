package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.SignInRequest;
import org.example.dto.request.SignUpRequest;
import org.example.dto.response.BooleanOperationResponse;
import org.example.dto.response.TokenResponse;
import org.example.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/sign-up")
    public BooleanOperationResponse signUp(@RequestBody SignUpRequest request) {
        return service.signUp(request);
    }

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return service.signIn(request);
    }

    @PostMapping("/sign-out")
    public BooleanOperationResponse exit() {
        return service.signOut();
    }
}