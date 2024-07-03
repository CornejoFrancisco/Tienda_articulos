package com.example.demo.entities.transformations.Forma_pago;

import com.example.demo.entities.DTO.Forma_pagoDto;
import com.example.demo.entities.Forma_pago;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Forma_pagoDtoMapper implements Function<Forma_pago, Forma_pagoDto> {
    @Override
    public Forma_pagoDto apply(Forma_pago formaPago){
        return new Forma_pagoDto(
                formaPago.getId_pago(),
                formaPago.getNombre()
        );
    }
}
