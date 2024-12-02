package com.duartedot.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequestDTO(
    @NotBlank(message = "O nome é obrigatório") String nome,
    @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 números")
        String cpf,
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 números")
        @NotBlank(message = "O CEP é obrigatório")
        String cep,
    @Pattern(regexp = ".{3,}", message = "O logradouro deve conter no mínimo 3 caracteres")
        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,
    @Pattern(regexp = ".{3,}", message = "O bairro deve conter no mínimo 3 caracteres")
        @NotBlank(message = "O bairro é obrigatório")
        String bairro,
    @Pattern(regexp = ".{3,}", message = "A cidade deve conter no mínimo 3 caracteres")
        @NotBlank(message = "A cidade é obrigatória")
        String cidade,
    @Pattern(regexp = ".{2,}", message = "O estado deve conter no mínimo 2 caracteres")
        @NotBlank(message = "O estado é obrigatório")
        String estado) {}
