package com.example.demo.controllers;

import com.example.demo.entities.DTO.SucursalDto;
import com.example.demo.services.Interfaces.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public ResponseEntity<List<SucursalDto>> getAll() {
        List<SucursalDto> values = sucursalService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDto> getById(@PathVariable("id") Long id) {
        SucursalDto value = sucursalService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody SucursalDto entity) {
        sucursalService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<SucursalDto> update(@RequestBody SucursalDto entity) {
        sucursalService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<SucursalDto> delete(@PathVariable("id") Long id) {
        sucursalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

