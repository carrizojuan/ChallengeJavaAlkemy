package com.javaChallenge.demo.security.service;

import com.javaChallenge.demo.security.dao.request.SignUpRequest;
import com.javaChallenge.demo.security.dao.request.SigninRequest;
import com.javaChallenge.demo.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}