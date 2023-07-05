package com.javaChallenge.demo.services.exception;

public class PeliculaNoEncontradoException extends RuntimeException{

    public PeliculaNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
