package com.github.cesargh.registrofauna.modelo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "paises",
        uniqueConstraints = { @UniqueConstraint(name = "UniqueConstraintPaisNombre", columnNames = {"nombre"}) }
)
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=20)
    private String nombre;

    @ManyToMany(mappedBy = "paises", fetch = FetchType.EAGER)
    private Set<Animal> animales = new HashSet<Animal>();

    public Pais() {
    }

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(Set<Animal> animales) {
        this.animales = animales;
    }

}
