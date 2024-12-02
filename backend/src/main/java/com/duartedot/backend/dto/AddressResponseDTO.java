package com.duartedot.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressResponseDTO(
    String cep,
    String logradouro,
    String bairro,
    @JsonProperty("cidade") @JsonAlias("localidade") String cidade,
    String estado,
    String uf) {}
