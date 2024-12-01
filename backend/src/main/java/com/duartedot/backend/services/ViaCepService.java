package com.duartedot.backend.services;

import com.duartedot.backend.dto.AddressResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
  private final RestTemplate restTemplate;

  public ViaCepService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public AddressResponseDTO getAddressByCep(String cep) {
    String url = "https://viacep.com.br/ws/" + cep + "/json/";
    return restTemplate.getForObject(url, AddressResponseDTO.class);
  }
}
