package com.gabriel.authorizationservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    
    private final DataSource dataSource;
    
    private final AuthenticationManager authenticationManager;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
            .checkTokenAccess("isAuthenticated()")
            .tokenKeyAccess("permitAll()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .jdbc(dataSource)
            .passwordEncoder(passwordEncoder);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(jdbcTokenStore());
    }
    
    @Bean
    protected TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
