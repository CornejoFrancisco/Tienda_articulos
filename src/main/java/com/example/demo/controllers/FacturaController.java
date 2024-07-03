package com.example.demo.controllers;

import com.example.demo.entities.DTO.FacturaDto;
import com.example.demo.services.Interfaces.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/factura")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDto>> getAll() {
        List<FacturaDto> values = facturaService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDto> getById(@PathVariable("id") Long id) {
        FacturaDto value = facturaService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody FacturaDto entity) {
        facturaService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<FacturaDto> delete(@PathVariable("id") Long id) {
        facturaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
