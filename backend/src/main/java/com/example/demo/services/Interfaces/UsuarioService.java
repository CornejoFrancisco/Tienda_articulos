package com.example.demo.services.Interfaces;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Usuario;
import org.springframework.http.ResponseEntity;


@org.springframework.stereotype.Service
public interface UsuarioService extends Service<UsuarioDto, Long> {
    ResponseEntity<String> addAdmi(UsuarioDto entity);
}
