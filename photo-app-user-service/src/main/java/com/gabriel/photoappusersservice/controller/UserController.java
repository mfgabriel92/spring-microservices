package com.gabriel.photoappusersservice.controller;

import com.gabriel.photoappusersservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return user;
    }
}

