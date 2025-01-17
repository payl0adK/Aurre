package com.ancapybara.aurre.service.impl;

import com.ancapybara.aurre.domain.entity.User;
import com.ancapybara.aurre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDefault {

  @Autowired
  UserRepository userRepository;

  public User findUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return user.get();
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public boolean saveUser(User user) {
    Optional<User> existUser = userRepository.findByUsername(user.getUsername());
    if (existUser.isPresent()) {
      return false;
    }
    userRepository.save(user);
    return true;
  }

  public boolean deleteUser(Long userId) {
    if (userRepository.findById(userId).isPresent()) {
      userRepository.deleteById(userId);
      return true;
    }
    return false;
  }
}
