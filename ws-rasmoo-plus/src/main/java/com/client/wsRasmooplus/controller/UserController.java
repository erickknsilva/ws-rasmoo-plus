package com.client.wsRasmooplus.controller;

import com.client.wsRasmooplus.dtos.UserDto;
import com.client.wsRasmooplus.model.User;
import com.client.wsRasmooplus.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public record UserController(
        @Autowired
        UserServiceImpl userServiceImpl) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody UserDto dto) {
        return userServiceImpl.create(dto);
    }

}
