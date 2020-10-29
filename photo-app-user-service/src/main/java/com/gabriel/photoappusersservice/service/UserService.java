package com.gabriel.photoappusersservice.service;

import com.gabriel.photoappusersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService extends JpaRepository<User, Integer> { }
