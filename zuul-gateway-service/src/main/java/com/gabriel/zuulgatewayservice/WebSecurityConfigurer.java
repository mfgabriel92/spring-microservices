package com.gabriel.zuulgatewayservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    public static final String CONFIG_URI = "/config-service/**";
    public static final String OAUTH_URI = "/authorization-service/**";
    public static final String USERS_URI = "/user-service/api/v1/users/**";
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
                .antMatchers(USERS_URI).hasRole("ADMIN")
                .antMatchers(CONFIG_URI, OAUTH_URI).permitAll()
                .anyRequest().authenticated().and()
            .oauth2ResourceServer().opaqueToken();
    }
}
