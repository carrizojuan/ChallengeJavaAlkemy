package com.javaChallenge.demo.security.controller;

import com.javaChallenge.demo.security.dao.request.SignUpRequest;
import com.javaChallenge.demo.security.dao.request.SigninRequest;
import com.javaChallenge.demo.security.dao.response.JwtAuthenticationResponse;
import com.javaChallenge.demo.security.service.AuthenticationService;
import com.javaChallenge.demo.services.MailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final MailService mailService;
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        JwtAuthenticationResponse response =  authenticationService.signup(request);
        try{
            mailService.sendWelcomeEmail(request.getEmail());
        }catch (IOException e){
            logger.error("Error al enviar el correo de bienvenida: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
