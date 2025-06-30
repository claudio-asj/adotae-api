package com.adotae.api.models;

import com.adotae.api.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Species species;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer age;

    private Double weight;

    private String color;

    private Boolean isNeutered;

    private Boolean hasDisability;

    private String disabilityDesc;

    @ElementCollection
    private List<String> vaccines;

    private Boolean isGoodWithKids;

    private Boolean isGoodWithPets;

    private Boolean houseTrained;

    private String location;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;

    @Lob
    private String description;

    private LocalDate rescueDate;

    private LocalDate createdAt;

    private String city;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] image1;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] image2;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] image3;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "favoriteAnimals")
    private List<User> usersWhoFavorited;

}
