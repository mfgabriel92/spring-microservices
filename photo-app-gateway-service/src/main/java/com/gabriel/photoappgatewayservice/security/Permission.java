package com.gabriel.photoappgatewayservice.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    USER_READ("USER:READ"),
    USER_WRITE("USER:WRITE");
    
    private final String name;
}
