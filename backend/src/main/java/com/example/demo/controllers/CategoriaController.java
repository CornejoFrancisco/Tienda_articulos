package com.example.demo.controllers;

import com.example.demo.entities.DTO.CategoriaDto;
import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.services.Interfaces.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> getAll() {
        List<CategoriaDto> values = categoriaService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getById(@PathVariable("id") Long id) {
        CategoriaDto value = categoriaService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CategoriaDto entity) {
        categoriaService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<CategoriaDto> update(@RequestBody CategoriaDto entity) {
        categoriaService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDto> delete(@PathVariable("id") Long id) {
        categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
