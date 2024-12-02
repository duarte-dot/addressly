package com.duartedot.backend.dto;

import jakarta.validation.constraints.Pattern;

public record UserPatchDTO(
    @Pattern(regexp = ".{3,}", message = "O nome deve conter no mínimo 3 caracteres") String nome,
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 números") String cpf,
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 números") String cep,
    @Pattern(regexp = ".{3,}", message = "O logradouro deve conter no mínimo 3 caracteres")
        String logradouro,
    @Pattern(regexp = ".{3,}", message = "O bairro deve conter no mínimo 3 caracteres")
        String bairro,
    @Pattern(regexp = ".{3,}", message = "A cidade deve conter no mínimo 3 caracteres")
        String cidade,
    @Pattern(regexp = ".{2,}", message = "O estado deve conter no mínimo 2 caracteres")
        String estado) {}
