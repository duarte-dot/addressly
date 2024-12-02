package com.duartedot.backend.services;

import com.duartedot.backend.dto.UserPatchDTO;
import com.duartedot.backend.exception.AlreadyRegisteredCpf;
import com.duartedot.backend.exception.UserNotFound;
import com.duartedot.backend.entity.User;
import com.duartedot.backend.repository.UserRepository;
import com.duartedot.backend.dto.UserRequestDTO;
import com.duartedot.backend.dto.UserResponseDTO;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

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

  public void patch(Long id, UserPatchDTO userPatch) {
    User existingUser =
        userRepository.findById(id).orElseThrow(() -> new UserNotFound("Usuário não encontrado."));

    if (userPatch.cpf() != null && !existingUser.getCpf().equals(userPatch.cpf())) {
      boolean cpfAlreadyExists = userRepository.existsByCpf(userPatch.cpf());
      if (cpfAlreadyExists) {
        throw new AlreadyRegisteredCpf("O CPF informado já está cadastrado.");
      }
    }

    updateFieldIfNotNull(existingUser::setCpf, userPatch.cpf());
    updateFieldIfNotNull(existingUser::setNome, userPatch.nome());
    updateFieldIfNotNull(existingUser::setCep, userPatch.cep());
    updateFieldIfNotNull(existingUser::setLogradouro, userPatch.logradouro());
    updateFieldIfNotNull(existingUser::setBairro, userPatch.bairro());
    updateFieldIfNotNull(existingUser::setCidade, userPatch.cidade());
    updateFieldIfNotNull(existingUser::setEstado, userPatch.estado());

    userRepository.save(existingUser);
  }

  private <T> void updateFieldIfNotNull(Consumer<T> setter, T value) {
    if (value != null) {
      setter.accept(value);
    }
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
