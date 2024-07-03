package com.example.demo.controllers;

import com.example.demo.entities.DTO.DetalleFacturaDto;
import com.example.demo.services.Interfaces.DetalleFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle")
public class DetalleFacturaController {

    private final DetalleFacturaService detalleFacturaService;

    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleFacturaDto>> getAll() {
        List<DetalleFacturaDto> values = detalleFacturaService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFacturaDto> getById(@PathVariable("id") Long id) {
        DetalleFacturaDto value = detalleFacturaService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody DetalleFacturaDto entity) {
        detalleFacturaService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<DetalleFacturaDto> update(@RequestBody DetalleFacturaDto entity) {
        detalleFacturaService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleFacturaDto> delete(@PathVariable("id") Long id) {
        detalleFacturaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
