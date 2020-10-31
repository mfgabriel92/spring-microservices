package com.gabriel.photoappusersservice.mapper;

import com.gabriel.photoappusersservice.model.User;
import com.gabriel.photoappusersservice.shared.UserRequest;
import com.gabriel.photoappusersservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;
    
    public UserResponse toModel(User model) {
        return mapper.map(model, UserResponse.class);
    }
    
    public User toDomainObject(UserRequest request) {
        return mapper.map(request, User.class);
    }
}
