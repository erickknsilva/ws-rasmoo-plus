package com.client.wsRasmooplus.controller;

import com.client.wsRasmooplus.dto.LoginRequest;
import com.client.wsRasmooplus.dto.token.TokenDto;
import com.client.wsRasmooplus.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.auth(request));
    }

}
