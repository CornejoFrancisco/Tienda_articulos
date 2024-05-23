package com.example.demo.controllers;


import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.services.Interfaces.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
    public ResponseEntity<Void> addAdmi(@RequestBody UsuarioDto entity) {
        usuarioService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/admin")
    public void add(@RequestBody UsuarioDto entity) {
        usuarioService.addAdmi(entity);

    }

    @PutMapping()
    public ResponseEntity<UsuarioDto> update(@RequestBody UsuarioDto entity) {
        usuarioService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
