package com.javaChallenge.demo.services;

import com.javaChallenge.demo.models.Genero;
import com.javaChallenge.demo.models.Pelicula;
import com.javaChallenge.demo.models.Personaje;
import com.javaChallenge.demo.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaService {
    @Autowired
    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> getPeliculas(){
        return peliculaRepository.findAll();
    }

    public List<Pelicula> getPeliculasPorTitulo(String titulo){
        return peliculaRepository.findByTituloContaining(titulo);
    }

    public Optional<Pelicula> getPeliculaById(Long id){

        return peliculaRepository.findById(id);
    }

    public List<Pelicula> getPeliculasPorOrden(String orden){
        if (orden.equalsIgnoreCase("asc")) {
            return peliculaRepository.findAllByOrderByTituloAsc();
        } else {
            return peliculaRepository.findAllByOrderByTituloDesc();
        }
    }

    public List<Pelicula> getPeliculasPorGenero(Long idGenero){
        return peliculaRepository.findByGeneros_Id(idGenero);
    }

    public Pelicula save(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    public void eliminarPelicula(Pelicula pelicula){
        peliculaRepository.delete(pelicula);
    }
}
