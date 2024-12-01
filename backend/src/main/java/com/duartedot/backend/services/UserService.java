package com.duartedot.backend.services;

import com.duartedot.backend.entity.User;
import com.duartedot.backend.repository.UserRepository;
import com.duartedot.backend.dto.UserRequestDTO;
import com.duartedot.backend.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;

  public List<UserResponseDTO> getAll() {
    return userRepository.findAll().stream().map(UserResponseDTO::new).toList();
  }

  public void create(UserRequestDTO user) {
    User newUser = new User(user);
    userRepository.save(newUser);
  }

  public void update(Long id, UserRequestDTO user) {
    User updatedUser = new User(user);
    updatedUser.setId(id);
    userRepository.save(updatedUser);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public void deleteAll() {
    userRepository.deleteAll();
  }
}
