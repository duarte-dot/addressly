package com.duartedot.backend.entity;

import com.duartedot.backend.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique = true)
  private String cpf;

  @Column(nullable = false)
  private String cep;

  @Column(nullable = false)
  private String logradouro;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String estado;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime dataCriacao;

  @UpdateTimestamp private LocalDateTime dataAtualizacao;

  public User(UserRequestDTO user) {
    this.nome = user.nome();
    this.cpf = user.cpf();
    this.cep = user.cep();
    this.logradouro = user.logradouro();
    this.bairro = user.bairro();
    this.cidade = user.cidade();
    this.estado = user.estado();
  }
}
