package com.example.main.service;

import java.util.Arrays;

import com.example.main.dto.UserRegistrationDto;
import com.example.main.model.Role;
import com.example.main.model.User;
import com.example.main.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User save(UserRegistrationDto urdto) {
    User user = new User(
      urdto.getFirstName(), urdto.getLastName(), 
      urdto.getEmail(), urdto.getPassword(), 
      Arrays.asList(new Role("ROLE_USER"))
    );
    
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }
}
