package com.github.cesargh.registrofauna.modelo;

public enum TipoAnimal {

    ANFIBIO(1,"anfibio"),
    AVE(2,"ave"),
    MAMIFERO(3,"mamífero"),
    PEZ(4,"pez"),
    REPTIL(5,"reptil");

    private final int id;
    private final String nombre;

    TipoAnimal (int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public static TipoAnimal fromInt(int id) {
        for (TipoAnimal tipo : TipoAnimal.values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoAnimal no identificado (id: " + id + ")");
    }

    public static TipoAnimal fromString(String nombre) {
        for (TipoAnimal tipo : TipoAnimal.values()) {
            if (tipo.nombre.equalsIgnoreCase(nombre)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoAnimal no identificado (nombre: " + nombre + ")");
    }

    public String toMenu() {
        return id + " - " + nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
    }

}
