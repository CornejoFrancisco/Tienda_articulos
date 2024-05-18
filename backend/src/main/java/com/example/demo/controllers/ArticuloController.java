package com.example.demo.controllers;


import com.example.demo.entities.DTO.ArticuloDto;
import com.example.demo.services.Interfaces.ArticuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {
    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }


    @GetMapping
    public ResponseEntity<List<ArticuloDto>> getAll() {
        List<ArticuloDto> values = articuloService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDto> getById(@PathVariable("id") Long id) {
        ArticuloDto value = articuloService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ArticuloDto entity) {
        articuloService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<ArticuloDto> update(@RequestBody ArticuloDto entity) {
        articuloService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ArticuloDto> delete(@PathVariable("id") Long id) {
        articuloService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
