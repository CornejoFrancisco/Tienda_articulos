package com.example.demo.controllers;

import com.example.demo.entities.DTO.ClienteDto;
import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.services.Interfaces.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll() {
        List<ClienteDto> values = clienteService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable("id") Long id) {
        ClienteDto value = clienteService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ClienteDto entity) {
        clienteService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping()
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteDto entity) {
        clienteService.update(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable("id") Long id) {
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
