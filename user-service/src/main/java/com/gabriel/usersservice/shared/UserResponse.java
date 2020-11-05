package com.gabriel.usersservice.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String code;
    
    private String firstName;
    
    private String lastName;

    private String email;
}
