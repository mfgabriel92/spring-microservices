package com.gabriel.photoappusersservice.shared;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "Minimum of 3 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Minimum of 3 characters")
    private String lastName;
    
    @NotBlank(message = "E-mail is required")
    @Email(message = "E-mail is invalid")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Minimum of 8 characters")
    private String password;
}
