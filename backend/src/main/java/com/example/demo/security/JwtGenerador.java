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
                .setSubject(nombre_usuario) // se construye el token, se le asocia a un nombre, se le setting date de expiracion
                //y el ultimo le hace un algoritmo que eso es magia.
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
                .getBody(); /*Obtenemos el claims(cuerpo) ya verificado del token el cual contendrá la información de
                nombre de usuario, fecha de expiración y firma del token*/
        return claims.getSubject(); //Devolvemos el nombre de usuario
    }

    public Boolean validarToken(String token) {
        try {
            //Validación del token por medio de la firma que contiene el String token(token)
            //Si son idénticas validara el token o caso contrario saltara la excepción de abajo
            Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_FIRMA).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
        }
    }
}
