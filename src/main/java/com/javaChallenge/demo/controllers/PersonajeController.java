package com.javaChallenge.demo.controllers;

import com.javaChallenge.demo.dtos.PersonajeDetalleDTO;
import com.javaChallenge.demo.models.Personaje;
import com.javaChallenge.demo.dtos.PersonajeDTO;
import com.javaChallenge.demo.services.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> obtenerPersonajes(@RequestParam(required = false) String nombre,
    @RequestParam(required = false) Integer edad, @RequestParam(required = false) Long pelicula) {

        List<Personaje> personajes = null;

        if (nombre!=null){
            personajes = personajeService.getPersonajesPorNombre(nombre);
        }
        else if (edad!=null){
            personajes = personajeService.getPersonajesPorEdad(edad);
        }else if(pelicula!=null){
            personajes = personajeService.getPersonajesPorPelicula(pelicula);
        } else{
            personajes = personajeService.getPersonajes();
        }


        if (personajes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<PersonajeDTO> personajesDTO = new ArrayList<>();

        for (Personaje personaje : personajes) {
            PersonajeDTO personajeDTO = new PersonajeDTO(personaje);
            personajesDTO.add(personajeDTO);
        }

        return ResponseEntity.ok(personajesDTO);
    }

    @PostMapping
    public ResponseEntity<Personaje> agregarPersonaje(@RequestBody Personaje personaje) {
        personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDetalleDTO> getPersonaje(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeService.getPersonajeById(id);
        if (personaje.isPresent()) {
            PersonajeDetalleDTO personajeDetalleDTO = new PersonajeDetalleDTO(personaje.get());
            return ResponseEntity.ok(personajeDetalleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDetalleDTO> actualizarPersonaje(@PathVariable Long id, @RequestBody PersonajeDetalleDTO personajeDTO) {
        Optional<Personaje> optionalPersonaje = personajeService.getPersonajeById(id);
        if (optionalPersonaje.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Personaje personaje = optionalPersonaje.get();
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setImagen(personajeDTO.getImagen());

        Personaje personajeactualizado = personajeService.save(personaje);
        PersonajeDetalleDTO personajeDTOActualizado = new PersonajeDetalleDTO(personajeactualizado);
        return ResponseEntity.ok(personajeDTOActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPersonaje(@PathVariable Long id){
        Optional<Personaje> optionalPersonaje = personajeService.getPersonajeById(id);
        if(optionalPersonaje.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        personajeService.eliminarPersonaje(optionalPersonaje.get());
        return ResponseEntity.noContent().build();
    }

}

