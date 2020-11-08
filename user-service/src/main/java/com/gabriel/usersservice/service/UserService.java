package com.gabriel.usersservice.service;

import com.gabriel.usersservice.exception.UserNotFoundException;
import com.gabriel.usersservice.model.User;
import com.gabriel.usersservice.repository.RoleRepository;
import com.gabriel.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    
    private final RoleRepository roleRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        return repository.save(user);
    }
    
    public User findOrFail(String code) {
        return repository
            .findByCode(code)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    
    public void findAndDeleteOrFail(String code) {
        repository.delete(findOrFail(code));
    }
}
