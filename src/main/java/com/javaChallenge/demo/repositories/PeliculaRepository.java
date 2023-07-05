package com.javaChallenge.demo.repositories;

import com.javaChallenge.demo.models.Pelicula;
import com.javaChallenge.demo.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByTituloContaining(String titulo);
    List<Pelicula> findAllByOrderByTituloAsc();
    List<Pelicula> findAllByOrderByTituloDesc();

    List<Pelicula> findByGeneros_Id(Long id);

}
