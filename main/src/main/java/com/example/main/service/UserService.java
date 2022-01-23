package com.example.main.service;

import com.example.main.dto.UserRegistrationDto;
import com.example.main.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User save(UserRegistrationDto urdto);
}
