package com.snapeq.blogapibackendv1.controllers;

import com.snapeq.blogapibackendv1.domain.dtos.AuthResponse;
import com.snapeq.blogapibackendv1.domain.dtos.LoginRequest;
import com.snapeq.blogapibackendv1.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/blogapi/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400) //24H
                .build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
