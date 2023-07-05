package com.javaChallenge.demo.controllers;

import com.javaChallenge.demo.dtos.PeliculaDTO;
import com.javaChallenge.demo.dtos.PeliculaDetalleDTO;
import com.javaChallenge.demo.models.Pelicula;
import com.javaChallenge.demo.services.PeliculaService;
import com.javaChallenge.demo.services.exception.PeliculaNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> obtenerPeliculas(@RequestParam(required = false) String titulo, @RequestParam(required = false) Long idGenero, @RequestParam(required = false) String orden){
        List<Pelicula> peliculas = null;
        if (titulo!=null){
            peliculas = peliculaService.getPeliculasPorTitulo(titulo);
        }else if (orden!=null){
            peliculas = peliculaService.getPeliculasPorOrden(orden);
        }else if(idGenero!=null){
            peliculas = peliculaService.getPeliculasPorGenero(idGenero);
        } else{
            peliculas = peliculaService.getPeliculas();
        }

        if (peliculas.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<PeliculaDTO> peliculasDTO = peliculas.stream()
                .map(PeliculaDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(peliculasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDetalleDTO> getPelicula(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        if (pelicula.isPresent()) {
            PeliculaDetalleDTO peliculaDetalleDTO = new PeliculaDetalleDTO(pelicula.get());
            return ResponseEntity.ok(peliculaDetalleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PeliculaDetalleDTO> agregarPelicula(@RequestBody Pelicula pelicula){
        PeliculaDetalleDTO peliculaDetalleDTO = new PeliculaDetalleDTO(peliculaService.save(pelicula));
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaDetalleDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDetalleDTO> actualizarPelicula(@PathVariable Long id, @RequestBody PeliculaDetalleDTO peliculaDetalleDTO){
        Optional<Pelicula> optionalPelicula = peliculaService.getPeliculaById(id);
        if (optionalPelicula.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Pelicula pelicula = optionalPelicula.get();
        pelicula.setCalificacion(peliculaDetalleDTO.getCalificacion());
        pelicula.setImagen(peliculaDetalleDTO.getImagen());
        pelicula.setTitulo(peliculaDetalleDTO.getTitulo());
        pelicula.setFechaCreacion(peliculaDetalleDTO.getFechaCreacion());
        PeliculaDetalleDTO peliculaDetalleDTOactualizado = new PeliculaDetalleDTO(peliculaService.save(pelicula));
        return ResponseEntity.ok(peliculaDetalleDTOactualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPelicula(@PathVariable Long id){
        Optional<Pelicula> optionalPelicula = peliculaService.getPeliculaById(id);
        if(optionalPelicula.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        peliculaService.eliminarPelicula(optionalPelicula.get());
        return ResponseEntity.noContent().build();
    }
}
