package com.github.cesargh.registrofauna.repositorio;

import com.github.cesargh.registrofauna.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    // JPA Consultas Derivadas
    Optional<Pais> findByNombreIgnoreCase(String valor);

    // JPA Consultas Nativas
    @Query( nativeQuery = true,
            value = "select p.nombre, count(a.id) as cantidad from paises p\n" +
                    "inner join animales_paises ap on ap.pais_id = p.id\n" +
                    "inner join animales a on a.id = ap.animal_id\n" +
                    "group by p.nombre\n" +
                    "order by p.nombre asc, cantidad asc")
    Optional<List> findByNameAnimalesCount();

}
