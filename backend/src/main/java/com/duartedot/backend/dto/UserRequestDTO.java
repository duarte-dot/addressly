package com.duartedot.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequestDTO(
    @NotBlank(message = "O nome é obrigatório") String nome,
    @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos")
        String cpf,
    @NotBlank(message = "O CEP é obrigatório") String cep,
    @NotBlank(message = "O logradouro é obrigatório") String logradouro,
    @NotBlank(message = "O bairro é obrigatório") String bairro,
    @NotBlank(message = "A cidade é obrigatória") String cidade,
    @NotBlank(message = "O estado é obrigatório") String estado) {}
