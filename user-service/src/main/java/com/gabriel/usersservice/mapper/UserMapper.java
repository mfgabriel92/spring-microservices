package com.gabriel.usersservice.mapper;

import com.gabriel.usersservice.model.User;
import com.gabriel.usersservice.shared.UserRequest;
import com.gabriel.usersservice.shared.UserResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    @NonNull
    private final ModelMapper mapper;
    
    public UserResponse toModel(User model) {
        return mapper.map(model, UserResponse.class);
    }
    
    public User toDomainObject(UserRequest request) {
        return mapper.map(request, User.class);
    }
    
    public List<UserResponse> toCollectionModel(List<User> users) {
        return users.stream().map(this::toModel).collect(Collectors.toList());
    }
    
    public void copyToDomainObject(UserRequest request, User user) {
        mapper.map(request, user);
    }
}
