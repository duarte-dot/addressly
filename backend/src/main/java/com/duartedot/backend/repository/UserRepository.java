package com.duartedot.backend.repository;

import com.duartedot.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByCpf(String cpf);
}
