package com.gabriel.photoappusersservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    
    @GetMapping
    public String status() {
        return "enabled on port " + env.getProperty("local.server.port");
    }
}
