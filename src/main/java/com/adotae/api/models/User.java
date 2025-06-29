package com.adotae.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Column(unique = true, nullable = false)
    private String cpf;

    private Boolean isActive;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    // Animais que o usuário é responsável
    @OneToMany(mappedBy = "users")
    private List<Animal> animals;

    // Animais favoritos do usuário
    @ManyToMany
    @JoinTable(
            name = "user_favorite_animals",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    private List<Animal> favoriteAnimals;
}
