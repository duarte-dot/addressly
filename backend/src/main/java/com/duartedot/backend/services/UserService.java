package com.duartedot.backend.services;

import com.duartedot.backend.exception.AlreadyRegisteredCpf;
import com.duartedot.backend.exception.UserNotFound;
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

    if (userRepository.existsByCpf(user.cpf())) {
      throw new AlreadyRegisteredCpf("O CPF informado já está cadastrado.");
    }

    User newUser = new User(user);
    userRepository.save(newUser);
  }

  public void update(Long id, UserRequestDTO user) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFound("Usuário não encontrado.");
    }

    User updatedUser = new User(user);
    updatedUser.setId(id);
    userRepository.save(updatedUser);
  }

  public void delete(Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFound("Usuário não encontrado.");
    }

    userRepository.deleteById(id);
  }

  public void deleteAll() {
    userRepository.deleteAll();
  }
}
