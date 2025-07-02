package com.adotae.api.repositories;

import com.adotae.api.models.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findByCity(String city, Pageable pageable);
}
