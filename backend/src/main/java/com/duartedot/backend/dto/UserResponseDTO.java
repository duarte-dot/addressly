package com.duartedot.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.duartedot.backend.entity.User;

public record UserResponseDTO(
    Long id,
    String nome,
    String cpf,
    String cep,
    String logradouro,
    String bairro,
    String cidade,
    String estado,
    @JsonProperty("data_criacao") String dataCriacao,
    @JsonProperty("data_atualizacao") String dataAtualizacao) {
  public UserResponseDTO(User user) {
    this(
        user.getId(),
        user.getNome(),
        user.getCpf(),
        user.getCep(),
        user.getLogradouro(),
        user.getBairro(),
        user.getCidade(),
        user.getEstado(),
        user.getDataCriacao().toString(),
        user.getDataAtualizacao().toString());
  }
}
