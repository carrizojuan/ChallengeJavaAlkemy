package com.javaChallenge.demo.models;

import com.javaChallenge.demo.dtos.PeliculaDTO;
import com.javaChallenge.demo.dtos.PeliculaDetalleDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private int calificacion;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "peliculas")
    private List<Personaje> personajes = new ArrayList<>();

    @ManyToMany(mappedBy = "peliculas")
    private List<Genero> generos;

    public Pelicula() {
    }

    public Pelicula(PeliculaDetalleDTO peliculaDetalleDTO){
        imagen = peliculaDetalleDTO.getImagen();
        titulo = peliculaDetalleDTO.getTitulo();
        fechaCreacion = peliculaDetalleDTO.getFechaCreacion();
        calificacion = peliculaDetalleDTO.getCalificacion();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
