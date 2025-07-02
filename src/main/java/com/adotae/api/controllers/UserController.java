package com.adotae.api.controllers;

import com.adotae.api.dtos.UserDTO;
import com.adotae.api.forms.UserForm;
import com.adotae.api.models.User;
import com.adotae.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> usersDto = userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(new UserDTO(optionalUser.get()));
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserForm form){
        try {
            User userSaved = userRepository.save(form.create());
            System.out.println("Senha recebida: " + form.getPassword());
            return ResponseEntity.ok(new UserDTO(userSaved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserForm form) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(Collections.singletonMap("error", "User not found"));
        }

        User updatedUser = form.update(id, userRepository);
        return ResponseEntity.ok(new UserDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body(Collections.singletonMap("error", "User not found"));
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
