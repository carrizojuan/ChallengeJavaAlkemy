package com.javaChallenge.demo.dtos;

import com.javaChallenge.demo.models.Pelicula;
import com.javaChallenge.demo.models.Personaje;

import java.util.ArrayList;
import java.util.List;

public class PersonajeDetalleDTO {
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;
    private List<String> peliculas;

    private PersonajeDetalleDTO(){};
    public PersonajeDetalleDTO(Personaje personaje) {
        this.id = personaje.getId();
        this.nombre = personaje.getNombre();
        this.imagen = personaje.getImagen();
        this.peliculas = new ArrayList<>();
        this.edad = personaje.getEdad();
        this.historia = personaje.getHistoria();
        this.peso = personaje.getPeso();
        for(Pelicula pelicula : personaje.getPeliculas()){
            this.peliculas.add(pelicula.getTitulo());
        }
    }

    public Long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public String getHistoria() {
        return historia;
    }

    public List<String> getPeliculas() {
        return peliculas;
    }
}
