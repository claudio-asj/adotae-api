package com.adotae.api.dtos;

import com.adotae.api.models.Animal;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private String sex;
    private Integer age;
    private Double weight;
    private String color;
    private Boolean isNeutered;
    private Boolean hasDisability;
    private String disabilityDesc;
    private String vaccines;
    private Boolean isGoodWithKids;
    private Boolean isGoodWithPets;
    private Boolean houseTrained;
    private String location;
    private String adoptionStatus;
    private String description;
    private String rescueDate;
    private String createdAt;
    private String city;
    private Long userId;

    private String base64Image1;
    private String base64Image2;
    private String base64Image3;

    public static AnimalDTO fromEntity(Animal animal) {
        AnimalDTO dto = new AnimalDTO();

        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSpecies(String.valueOf(animal.getSpecies()));
        dto.setBreed(animal.getBreed());
        dto.setSex(String.valueOf(animal.getSex()));
        dto.setAge(animal.getAge());
        dto.setWeight(animal.getWeight());
        dto.setColor(animal.getColor());
        dto.setIsNeutered(animal.getIsNeutered());
        dto.setHasDisability(animal.getHasDisability());
        dto.setDisabilityDesc(animal.getDisabilityDesc());
        dto.setVaccines(String.valueOf(animal.getVaccines()));
        dto.setIsGoodWithKids(animal.getIsGoodWithKids());
        dto.setIsGoodWithPets(animal.getIsGoodWithPets());
        dto.setHouseTrained(animal.getHouseTrained());
        dto.setLocation(animal.getLocation());
        dto.setAdoptionStatus(String.valueOf(animal.getAdoptionStatus()));
        dto.setDescription(animal.getDescription());
        dto.setRescueDate(animal.getRescueDate() != null ? animal.getRescueDate().toString() : null);
        dto.setCreatedAt(animal.getCreatedAt() != null ? animal.getCreatedAt().toString() : null);
        dto.setCity(animal.getCity());
        dto.setUserId(animal.getUser() != null ? animal.getUser().getId() : null);

        if (animal.getImage1() != null) {
            dto.setBase64Image1(Base64.getEncoder().encodeToString(animal.getImage1()));
        }
        if (animal.getImage2() != null) {
            dto.setBase64Image2(Base64.getEncoder().encodeToString(animal.getImage2()));
        }
        if (animal.getImage3() != null) {
            dto.setBase64Image3(Base64.getEncoder().encodeToString(animal.getImage3()));
        }

        return dto;
    }
}
