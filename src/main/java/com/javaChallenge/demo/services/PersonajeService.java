package com.javaChallenge.demo.services;

import com.javaChallenge.demo.models.Pelicula;
import com.javaChallenge.demo.models.Personaje;
import com.javaChallenge.demo.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonajeService {

        @Autowired
        private final PersonajeRepository personajeRepository;
        public PersonajeService(PersonajeRepository personajeRepository) {
            this.personajeRepository = personajeRepository;
        }
        public List<Personaje> getPersonajes(){
            return personajeRepository.findAll();
        }


        public List<Personaje> getPersonajesPorNombre(String nombre){
            return personajeRepository.findByNombreContaining(nombre);
        }



        public List<Personaje> getPersonajesPorEdad(int edad){
            return personajeRepository.findByEdad(edad);
        }


        public List<Personaje> getPersonajesPorPelicula(Long idPelicula){
            return personajeRepository.findByPeliculas_Id(idPelicula);
        }



        public Optional<Personaje> getPersonajeById(Long id){
            return personajeRepository.findById(id);
        }

        public Personaje save(Personaje personaje) {
            return personajeRepository.save(personaje);
        }


        public void eliminarPersonaje(Personaje personaje){
            personajeRepository.delete(personaje);
        }
}
