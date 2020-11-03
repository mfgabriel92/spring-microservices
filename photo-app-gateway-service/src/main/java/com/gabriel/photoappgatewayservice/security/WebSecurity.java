package com.gabriel.photoappgatewayservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.gabriel.photoappgatewayservice.security.Permission.USER_DELETE;
import static com.gabriel.photoappgatewayservice.security.Permission.USER_READ;
import static com.gabriel.photoappgatewayservice.security.Permission.USER_WRITE;
import static com.gabriel.photoappgatewayservice.security.Roles.ADMIN;
import static com.gabriel.photoappgatewayservice.security.Roles.USER;
import static com.gabriel.photoappgatewayservice.security.Roles.VISITOR;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final static String USERS_URI = "/photo-app-user-service/api/v1/users/**";
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("foo"))
            .authorities(ADMIN.getGrantedAuthorities()).and()
            
            .withUser("user")
            .password(passwordEncoder().encode("foo"))
            .authorities(USER.getGrantedAuthorities()).and()
            
            .withUser("visitor")
            .password(passwordEncoder().encode("foo"))
            .authorities(VISITOR.getGrantedAuthorities());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, USERS_URI).hasAuthority(USER_READ.getPermission())
            .antMatchers(HttpMethod.PUT, USERS_URI).hasAuthority(USER_WRITE.getPermission())
            .antMatchers(HttpMethod.POST, USERS_URI).hasAuthority(USER_WRITE.getPermission())
            .antMatchers(HttpMethod.DELETE, USERS_URI).hasAuthority(USER_DELETE.getPermission())
            .antMatchers(USERS_URI).hasAnyRole(ADMIN.name(), USER.name())
            .anyRequest().authenticated().and()
            .httpBasic();
    }
    
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
