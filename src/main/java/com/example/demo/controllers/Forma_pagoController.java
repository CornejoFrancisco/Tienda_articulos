package com.example.demo.controllers;

import com.example.demo.entities.DTO.Forma_pagoDto;
import com.example.demo.services.Interfaces.FormaPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/forma_pago")
public class Forma_pagoController {

    private final FormaPagoService formaPagoService;

    public Forma_pagoController(FormaPagoService formaPagoService) {
        this.formaPagoService = formaPagoService;
    }

    @GetMapping
    public ResponseEntity<List<Forma_pagoDto>> getAll() {
        List<Forma_pagoDto> values = formaPagoService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forma_pagoDto> getById(@PathVariable("id") Long id) {
        Forma_pagoDto value = formaPagoService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Forma_pagoDto entity) {
        formaPagoService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<Forma_pagoDto> update(@RequestBody Forma_pagoDto entity) {
        formaPagoService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Forma_pagoDto> delete(@PathVariable("id") Long id) {
        formaPagoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
