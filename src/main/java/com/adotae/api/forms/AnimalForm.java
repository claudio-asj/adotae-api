package com.adotae.api.forms;

import com.adotae.api.enums.AdoptionStatus;
import com.adotae.api.enums.Sex;
import com.adotae.api.enums.Species;
import com.adotae.api.models.Animal;
import com.adotae.api.models.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Data
public class AnimalForm {

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
    private List<String> vaccines;   // List<String> para aceitar array JSON
    private Boolean isGoodWithKids;
    private Boolean isGoodWithPets;
    private Boolean houseTrained;
    private String location;
    private String adoptionStatus;
    private String description;
    private String rescueDate;  // String para receber data, parse depois
    private String city;

    private String base64Image1;
    private String base64Image2;
    private String base64Image3;

    public Animal toAnimal(User user) {
        Animal animal = new Animal();

        animal.setName(this.name);
        animal.setSpecies(Species.valueOf(this.species));
        animal.setBreed(this.breed);
        animal.setSex(Sex.valueOf(this.sex));
        animal.setAge(this.age);
        animal.setWeight(this.weight);
        animal.setColor(this.color);
        animal.setIsNeutered(this.isNeutered);
        animal.setHasDisability(this.hasDisability);
        animal.setDisabilityDesc(this.disabilityDesc);
        animal.setVaccines(this.vaccines != null ? this.vaccines : Collections.emptyList());
        animal.setIsGoodWithKids(this.isGoodWithKids);
        animal.setIsGoodWithPets(this.isGoodWithPets);
        animal.setHouseTrained(this.houseTrained);
        animal.setLocation(this.location);
        animal.setAdoptionStatus(AdoptionStatus.valueOf(this.adoptionStatus));
        animal.setDescription(this.description);
        animal.setRescueDate(LocalDate.parse(this.rescueDate));
        animal.setCity(this.city);
        animal.setUser(user);

        if (this.base64Image1 != null && !this.base64Image1.isEmpty()) {
            animal.setImage1(Base64.getDecoder().decode(this.base64Image1));
        }
        if (this.base64Image2 != null && !this.base64Image2.isEmpty()) {
            animal.setImage2(Base64.getDecoder().decode(this.base64Image2));
        }
        if (this.base64Image3 != null && !this.base64Image3.isEmpty()) {
            animal.setImage3(Base64.getDecoder().decode(this.base64Image3));
        }

        return animal;
    }
}
