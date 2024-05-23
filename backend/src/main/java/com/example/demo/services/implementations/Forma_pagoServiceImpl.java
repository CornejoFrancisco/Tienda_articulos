package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.Forma_pagoDto;
import com.example.demo.entities.Forma_pago;
import com.example.demo.entities.Unidad_medida;
import com.example.demo.entities.transformations.Forma_pago.Forma_pagoDtoMapper;
import com.example.demo.entities.transformations.Forma_pago.Forma_pagoMapper;
import com.example.demo.repositories.FormaPagoRepository;
import com.example.demo.services.Interfaces.FormaPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class Forma_pagoServiceImpl implements FormaPagoService {

    private FormaPagoRepository formaPagoRepository;
    private Forma_pagoMapper formaPagoMapper;
    private Forma_pagoDtoMapper formaPagoDtoMapper;

    public Forma_pagoServiceImpl(FormaPagoRepository formaPagoRepository, Forma_pagoMapper formaPagoMapper, Forma_pagoDtoMapper formaPagoDtoMapper) {
        this.formaPagoRepository = formaPagoRepository;
        this.formaPagoMapper = formaPagoMapper;
        this.formaPagoDtoMapper = formaPagoDtoMapper;
    }

    @Override
    public ResponseEntity<String> add(Forma_pagoDto entity) {
        Forma_pago formaPago = new Forma_pago();
        formaPago.setNombre(entity.getNombre());
        formaPagoRepository.save(formaPago);
        return new ResponseEntity<>("Registro de categoria forma pago", HttpStatus.OK);
    }

    @Override
    public Forma_pagoDto getById(Long id) {
        Optional<Forma_pago> formaPago = formaPagoRepository.findById(id);

        return formaPago.map(formaPagoDtoMapper).orElseThrow();
    }

    @Override
    public List<Forma_pagoDto> getAll() {
        List<Forma_pago> forma_pagos = formaPagoRepository.findAll();

        return forma_pagos.stream().map(formaPagoDtoMapper).toList();
    }

    @Override
    public Forma_pagoDto delete(Long id) {
        Optional<Forma_pago> formaPago = formaPagoRepository.findById(id);
        formaPago.ifPresent(formaPagoRepository :: delete);
        return formaPago.map(formaPagoDtoMapper).orElseThrow();
    }

    @Override
    public void update(Forma_pagoDto entity) {
        Optional<Forma_pago> formaPago = formaPagoRepository.findById(entity.getId_pago());
        formaPago.ifPresent(formaPago1 -> {
            formaPago1.setNombre(entity.getNombre());
            formaPagoRepository.save(formaPago1);
        });

    }
}
