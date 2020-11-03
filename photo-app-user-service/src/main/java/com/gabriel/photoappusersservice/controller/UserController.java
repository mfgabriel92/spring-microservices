package com.gabriel.photoappusersservice.controller;

import com.gabriel.photoappusersservice.mapper.UserMapper;
import com.gabriel.photoappusersservice.model.User;
import com.gabriel.photoappusersservice.repository.UserRepository;
import com.gabriel.photoappusersservice.service.UserService;
import com.gabriel.photoappusersservice.shared.UserRequest;
import com.gabriel.photoappusersservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper mapper;
    
    private final UserRepository repository;
    
    private final UserService service;
    
    @GetMapping
    public List<UserResponse> findAll() {
        return mapper.toCollectionModel(repository.findAll());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@Valid @RequestBody UserRequest userRequest) {
        User user = mapper.toDomainObject(userRequest);
        return mapper.toModel(service.save(user));
    }
    
    @PutMapping("{code}")
    public UserResponse save(@PathVariable String code, @Valid @RequestBody UserRequest userRequest) {
        User user = service.findOrFail(code);
        mapper.copyToDomainObject(userRequest, user);
        return mapper.toModel(service.save(user));
    }
    
    @DeleteMapping("{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String code) {
        service.findAndDeleteOrFail(code);
    }
}
