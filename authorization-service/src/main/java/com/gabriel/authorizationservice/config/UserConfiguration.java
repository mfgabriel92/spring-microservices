package com.gabriel.authorizationservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder.encode("user"))
            .roles("USER")
            .authorities("CAN_READ", "CAN_WRITE");
    }
}
