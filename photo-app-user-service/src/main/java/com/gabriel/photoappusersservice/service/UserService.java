package com.gabriel.photoappusersservice.service;

import com.gabriel.photoappusersservice.exception.UserNotFoundException;
import com.gabriel.photoappusersservice.model.User;
import com.gabriel.photoappusersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    
    public User findOrFail(Integer id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    
    public void findAndDeleteOrFail(Integer id) {
        findOrFail(id);
        repository.deleteById(id);
    }
}
