package com.example.main.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.example.main.dto.UserRegistrationDto;
import com.example.main.model.Role;
import com.example.main.model.User;
import com.example.main.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private BCryptPasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User save(UserRegistrationDto urdto) {
    User user = new User(
      urdto.getFirstName(), urdto.getLastName(), 
      urdto.getEmail(), passwordEncoder.encode(urdto.getPassword()), 
      Arrays.asList(new Role("ROLE_USER"))
    );    
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username");
    }
    return new org.springframework.security.core.userdetails
      .User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream()
      .map(r -> new SimpleGrantedAuthority(r.getName()))
      .collect(Collectors.toList());
  }
}
