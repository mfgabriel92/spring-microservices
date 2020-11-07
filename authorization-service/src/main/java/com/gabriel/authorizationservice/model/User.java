package com.gabriel.authorizationservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    public User(User user) {
        this.code = user.getCode();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.isAccountNonExpired = user.getIsAccountNonExpired();
        this.isCredentialsNonExpired = user.getIsCredentialsNonExpired();
        this.isAccountNonLocked = user.getIsAccountNonLocked();
        this.roles = user.getRoles();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String code;
    
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private Boolean enabled;
    
    private Boolean isAccountNonExpired;
    
    private Boolean isCredentialsNonExpired;
    
    private Boolean isAccountNonLocked;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
