package com.gabriel.photoappusersservice.repository;

import com.gabriel.photoappusersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { }
