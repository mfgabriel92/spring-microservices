package com.gabriel.authorizationservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    
    private final DataSource dataSource;
    
    private final AuthenticationManager authenticationManager;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAuthenticated()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .jdbc(dataSource)
            .passwordEncoder(passwordEncoder);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter()));
        
        endpoints
            .authenticationManager(authenticationManager)
            .accessTokenConverter(jwtAccessTokenConverter())
            .tokenEnhancer(tokenEnhancerChain)
            .approvalStore(approvalStore(endpoints.getTokenStore()))
            .tokenStore(jdbcTokenStore())
            .tokenGranter(tokenGranter(endpoints));
    }
    
    @Bean
    protected TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    
    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        String path = "keystore/sms.jks";
        String alias = "sms";
        String password = "QwnmzxoP";
    
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        ClassPathResource jksFile = new ClassPathResource(path);
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(jksFile, password.toCharArray());
        KeyPair keyPair = factory.getKeyPair(alias);
        
        converter.setKeyPair(keyPair);
        
        return converter;
    }
    
    private ApprovalStore approvalStore(TokenStore tokenStore) {
        TokenApprovalStore tokenApprovalStore = new TokenApprovalStore();
        tokenApprovalStore.setTokenStore(tokenStore);
        
        return tokenApprovalStore;
    }
    
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = Collections.singletonList(endpoints.getTokenGranter());
        return new CompositeTokenGranter(granters);
    }
}
