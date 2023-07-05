package com.javaChallenge.demo.dtos;

import com.javaChallenge.demo.models.Pelicula;
import com.javaChallenge.demo.models.Personaje;

import java.util.ArrayList;
import java.util.List;

public class PeliculaDetalleDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private int calificacion;
    private List<String> personajes = new ArrayList<>();

    public PeliculaDetalleDTO(){

    }
    public PeliculaDetalleDTO(Pelicula pelicula) {
        this.id = pelicula.getId();
        this.titulo = pelicula.getTitulo();
        this.fechaCreacion = pelicula.getFechaCreacion();
        imagen = pelicula.getImagen();
        calificacion = pelicula.getCalificacion();
    }


    public Long getId() {
        return id;
    }
    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public List<String> getPersonajes() {
        return personajes;
    }

}
