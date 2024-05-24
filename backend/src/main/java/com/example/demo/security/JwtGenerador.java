package com.example.demo.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerador {

    public String generarToken(Authentication authentication){
        String nombre_usuario =authentication.getName();
        Date tiempo_actual = new Date();
        Date expiracionToken = new Date(tiempo_actual.getTime() + ConstantesSeguridad.JWT_EXPIRATION_TOKEN);

        String token = Jwts.builder()
                .setSubject(nombre_usuario)
                .setIssuedAt(new Date())
                .setExpiration(expiracionToken)
                .signWith(SignatureAlgorithm.HS512, ConstantesSeguridad.JWT_FIRMA)
                .compact();
        return token;
    }

    public String obtenerUsernameDeJwt(String token) {
        Claims claims = Jwts.parser() // El método parser se utiliza con el fin de analizar el token
                .setSigningKey(ConstantesSeguridad.JWT_FIRMA)// Establece la clave de firma, que se utiliza para verificar la firma del token
                .parseClaimsJws(token) //Se utiliza para verificar la firma del token, apartir del String "token"
                .getBody();
        return claims.getSubject();
    }

    public Boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_FIRMA).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
        }
    }
}
