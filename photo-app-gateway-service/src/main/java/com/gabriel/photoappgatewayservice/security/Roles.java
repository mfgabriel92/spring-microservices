package com.gabriel.photoappgatewayservice.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static com.gabriel.photoappgatewayservice.security.Permission.USER_READ;
import static com.gabriel.photoappgatewayservice.security.Permission.USER_WRITE;

@Getter
@AllArgsConstructor
public enum Roles {
    ADMIN(Sets.newHashSet(USER_READ)),
    USER(Sets.newHashSet(USER_READ, USER_WRITE)),
    VISITOR(Sets.newHashSet());
    
    private final Set<Permission> permissions;
}
