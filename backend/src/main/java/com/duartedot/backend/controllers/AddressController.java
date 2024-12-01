package com.duartedot.backend.controllers;

import com.duartedot.backend.services.ViaCepService;
import com.duartedot.backend.dto.AddressResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

  @Autowired private ViaCepService addressService;

  @GetMapping("/{cep}")
  public ResponseEntity<AddressResponseDTO> getAddressByCep(@PathVariable String cep) {
    AddressResponseDTO address = addressService.getAddressByCep(cep);
    return ResponseEntity.ok(address); // Retorna 200 OK com os dados do endereço
  }
}
