package com.gabriel.photoappusersservice.controller;

import com.gabriel.photoappusersservice.mapper.UserMapper;
import com.gabriel.photoappusersservice.model.User;
import com.gabriel.photoappusersservice.service.UserService;
import com.gabriel.photoappusersservice.shared.UserRequest;
import com.gabriel.photoappusersservice.shared.UserResponse;
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
    private final UserMapper mapper;
    
    private final UserService service;
    
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        User user = mapper.toDomainObject(userRequest);
        return mapper.toModel(service.save(user));
    }
}

