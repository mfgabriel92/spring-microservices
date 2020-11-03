package com.gabriel.photoappgatewayservice.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gabriel.photoappgatewayservice.security.Permission.USER_DELETE;
import static com.gabriel.photoappgatewayservice.security.Permission.USER_READ;
import static com.gabriel.photoappgatewayservice.security.Permission.USER_WRITE;

@Getter
@AllArgsConstructor
public enum Roles {
    VISITOR(Sets.newHashSet()),
    USER(Sets.newHashSet(USER_READ, USER_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, USER_DELETE));
    
    private final Set<Permission> permissions;
    
    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        List<SimpleGrantedAuthority> permissions = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
        
        permissions.add(new SimpleGrantedAuthority("ROLE_" + name()));
        
        return permissions;
    }
}
