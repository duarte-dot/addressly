package com.duartedot.backend.controllers;

import com.duartedot.backend.dto.UserPatchDTO;
import com.duartedot.backend.services.UserService;
import com.duartedot.backend.dto.UserRequestDTO;
import com.duartedot.backend.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAll() {
    List<UserResponseDTO> users = userService.getAll();
    return ResponseEntity.ok(users);
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody @Valid UserRequestDTO user) {
    userService.create(user);
    return ResponseEntity.status(201).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
      @PathVariable Long id, @Valid @RequestBody UserRequestDTO user) {
    userService.update(id, user);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> patch(
      @PathVariable Long id, @Valid @RequestBody UserPatchDTO userPatch) {
    userService.patch(id, userPatch);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    userService.deleteAll();
    return ResponseEntity.noContent().build();
  }
}
