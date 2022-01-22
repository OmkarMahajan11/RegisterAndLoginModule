package com.example.main.service;

import com.example.main.dto.UserRegistrationDto;
import com.example.main.model.User;

public interface UserService {
  User save(UserRegistrationDto u);
}
