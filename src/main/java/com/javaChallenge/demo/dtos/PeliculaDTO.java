package com.javaChallenge.demo.dtos;

import com.javaChallenge.demo.models.Pelicula;

public class PeliculaDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;


    public PeliculaDTO(Pelicula pelicula) {
        id = pelicula.getId();
        this.imagen = pelicula.getImagen();
        this.titulo = pelicula.getTitulo();
        this.fechaCreacion = pelicula.getFechaCreacion();
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

}
