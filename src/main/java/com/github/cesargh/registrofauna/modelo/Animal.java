package com.github.cesargh.registrofauna.modelo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "animales",
        uniqueConstraints = { @UniqueConstraint(name = "UniqueConstraintAnimalNombre", columnNames = {"nombre"}) }
)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private TipoAnimal tipo;

    @Column(nullable=false, length=20)
    private String nombre;

    @Column(nullable=false)
    private int anio;

    @Column(nullable=false)
    private boolean extincion;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "animales_paises",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "pais_id"))
    private Set<Pais> paises = new HashSet<Pais>();

    public Animal() {
    }

    public Animal(TipoAnimal tipo, String nombre, int anio, boolean extincion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.anio = anio;
        this.extincion = extincion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAnimal getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnimal tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isExtincion() {
        return extincion;
    }

    public void setExtincion(boolean extincion) {
        this.extincion = extincion;
    }

    public Set<Pais> getPaises() {
        return paises;
    }

    public void setPaises(Set<Pais> paises) {
        this.paises = paises;
    }

    @Override
    public String toString() {
        return "Animal {" +
                "Id=" + id +
                ", Tipo=" + tipo +
                ", Nombre=" + nombre +
                ", Año=" + anio +
                ", Extinto=" + (extincion ? "Sí" : "No") +
                ", Paises=" + paises.size() +
                " }";
    }

}
