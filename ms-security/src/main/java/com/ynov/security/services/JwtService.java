package com.ynov.security.services;


public interface JwtService {
    String generateToken(String username,String password);
}
