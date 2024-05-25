package com.example.demo.controllers;


import com.example.demo.entities.DTO.*;
import com.example.demo.security.JwtGenerador;
import com.example.demo.services.Interfaces.UsuarioService;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private AuthenticationManager authenticationManager;
    private JwtGenerador jwtGenerador;
    public UsuarioController(UsuarioService usuarioService, AuthenticationManager authenticationManager, JwtGenerador jwtGenerador) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerador = jwtGenerador;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAll() {
        List<UsuarioDto> values = usuarioService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable("id") Long id) {
        UsuarioDto value = usuarioService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> addAdmi(@RequestBody UsuarioDtoUser entity) {
        usuarioService.addUser(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> add(@RequestBody UsuarioDtoAdmin entity) {
        usuarioService.addAdmi(entity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<UsuarioDto> update(@RequestBody UsuarioDto entity) {
        usuarioService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("ASdssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssASdssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }
}
