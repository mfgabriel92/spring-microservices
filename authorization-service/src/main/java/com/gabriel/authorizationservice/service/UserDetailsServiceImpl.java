package com.gabriel.authorizationservice.service;

import com.gabriel.authorizationservice.model.AuthUser;
import com.gabriel.authorizationservice.model.User;
import com.gabriel.authorizationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Credentials do not match"));
        
        UserDetails userDetails = new AuthUser(user);
        new AccountStatusUserDetailsChecker().check(userDetails);
        
        return userDetails;
    }
}
