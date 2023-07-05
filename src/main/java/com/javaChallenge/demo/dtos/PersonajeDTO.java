package com.javaChallenge.demo.dtos;

import com.javaChallenge.demo.models.Personaje;

public class PersonajeDTO {
    private Long id;
    private String nombre;
    private String imagen;

    public PersonajeDTO(Personaje personaje) {
        this.id = personaje.getId();
        this.nombre = personaje.getNombre();
        this.imagen = personaje.getImagen();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
}