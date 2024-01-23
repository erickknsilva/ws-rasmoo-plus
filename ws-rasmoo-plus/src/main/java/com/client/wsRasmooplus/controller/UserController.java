package com.client.wsRasmooplus.controller;

import com.client.wsRasmooplus.dto.UserDto;
import com.client.wsRasmooplus.model.User;
import com.client.wsRasmooplus.service.impl.UserServiceImpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody UserDto dto) {
        return userServiceImpl.create(dto);
    }

}
