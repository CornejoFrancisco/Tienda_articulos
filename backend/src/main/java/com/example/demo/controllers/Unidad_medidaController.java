package com.example.demo.controllers;

import com.example.demo.entities.DTO.Unidad_medidaDto;
import com.example.demo.services.Interfaces.UnidadMedidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/unidad_medida")
public class Unidad_medidaController {

    private final UnidadMedidaService unidadMedidaService;

    public Unidad_medidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }


    @GetMapping
    public ResponseEntity<List<Unidad_medidaDto>> getAll() {
        List<Unidad_medidaDto> values = unidadMedidaService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidad_medidaDto> getById(@PathVariable("id") Long id) {
        Unidad_medidaDto value = unidadMedidaService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Unidad_medidaDto entity) {
        unidadMedidaService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<Unidad_medidaDto> update(@RequestBody Unidad_medidaDto entity) {
        unidadMedidaService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Unidad_medidaDto> delete(@PathVariable("id") Long id) {
        unidadMedidaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
