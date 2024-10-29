package com.github.cesargh.registrofauna.repositorio;

import com.github.cesargh.registrofauna.modelo.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // JPA Derived Queries (JPA Consultas Derivadas)
    Optional<Animal> findByNombreIgnoreCase(String valor);
    List<Animal> findByExtincion(boolean valor);

}
