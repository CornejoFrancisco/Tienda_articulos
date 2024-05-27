package com.example.demo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class ConstantesSeguridad {
    public static final long JWT_EXPIRATION_TOKEN = 3600000; //equivaler a 5 min, donde 60000 = a 1 min
    public static final SecretKey JWT_FIRMA = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
