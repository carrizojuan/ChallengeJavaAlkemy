package com.javaChallenge.demo.repositories;

import com.javaChallenge.demo.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findByNombreContaining(String nombre);
    List<Personaje> findByEdad(int edad);
    List<Personaje> findByPeliculas_Id(Long peliculaId);

}
