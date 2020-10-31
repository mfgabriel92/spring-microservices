package com.gabriel.photoappusersservice.service;

import com.gabriel.photoappusersservice.model.User;
import com.gabriel.photoappusersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    
    public User save(User user) {
        return repository.save(user);
    }
}
