package com.example.main.service;

import java.util.Arrays;

import com.example.main.dto.UserRegistrationDto;
import com.example.main.model.Role;
import com.example.main.model.User;
import com.example.main.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User save(UserRegistrationDto u) {
    User user = new User(
      u.getFirstName(), u.getLastName(), 
      u.getEmail(), u.getPassword(), 
      Arrays.asList(new Role("ROLE_USER"))
    );
    
    return userRepository.save(user);
  }
}
