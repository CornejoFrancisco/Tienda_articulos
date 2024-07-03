package com.example.demo.services.Interfaces;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.DTO.UsuarioDtoAdmin;
import com.example.demo.entities.DTO.UsuarioDtoUser;
import org.springframework.http.ResponseEntity;


@org.springframework.stereotype.Service
public interface UsuarioService extends Service<UsuarioDto, Long> {
    ResponseEntity<String> addAdmi(UsuarioDtoAdmin entity);

    ResponseEntity<String> addUser(UsuarioDtoUser entity);
}
