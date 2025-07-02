package com.adotae.api.controllers;

import com.adotae.api.dtos.AnimalDTO;
import com.adotae.api.enums.AdoptionStatus;
import com.adotae.api.enums.Sex;
import com.adotae.api.enums.Species;
import com.adotae.api.forms.AnimalForm;
import com.adotae.api.models.Animal;
import com.adotae.api.models.User;
import com.adotae.api.repositories.AnimalRepository;
import com.adotae.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<Page<AnimalDTO>> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        if (userId == null) {
            Page<Animal> animalsPage = animalRepository.findAll(pageable);
            return ResponseEntity.ok(animalsPage.map(AnimalDTO::fromEntity));
        } else {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Page<Animal> animalsPage = animalRepository.findByCity(user.getCity(), pageable);
            return ResponseEntity.ok(animalsPage.map(AnimalDTO::fromEntity));
        }
    }
    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getById(@PathVariable Long id) {
        Optional<Animal> optional = animalRepository.findById(id);
        return optional.map(animal -> ResponseEntity.ok(AnimalDTO.fromEntity(animal)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar animal
    @PostMapping("/{userId}")
    public ResponseEntity<AnimalDTO> createAnimal(@PathVariable Long userId, @RequestBody AnimalForm form) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Animal animal = form.toAnimal(user);   // ou form.toAnimal(userRepository) se precisar
        animal.setUser(user);
        animal = animalRepository.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnimalDTO.fromEntity(animal));
    }

    // Atualizar animal
    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody AnimalForm form) {
        Optional<Animal> optional = animalRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Animal animal = optional.get();
        // Atualiza os campos (exemplo simples)
        animal.setName(form.getName());
        animal.setSpecies(Species.valueOf(form.getSpecies()));
        animal.setBreed(form.getBreed());
        animal.setSex(Sex.valueOf(form.getSex()));
        animal.setAge(form.getAge());
        animal.setWeight(form.getWeight());
        animal.setColor(form.getColor());
        animal.setIsNeutered(form.getIsNeutered());
        animal.setHasDisability(form.getHasDisability());
        animal.setDisabilityDesc(form.getDisabilityDesc());
        animal.setVaccines(form.getVaccines());
        animal.setIsGoodWithKids(form.getIsGoodWithKids());
        animal.setIsGoodWithPets(form.getIsGoodWithPets());
        animal.setHouseTrained(form.getHouseTrained());
        animal.setLocation(form.getLocation());
        animal.setAdoptionStatus(AdoptionStatus.valueOf(form.getAdoptionStatus()));
        animal.setDescription(form.getDescription());
        animal.setRescueDate(LocalDate.parse(form.getRescueDate()));
        animal.setCity(form.getCity());
        // Note: não atualiza createdAt para não perder histórico

        Animal updated = animalRepository.save(animal);
        return ResponseEntity.ok(updated);
    }

    // Deletar animal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!animalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
