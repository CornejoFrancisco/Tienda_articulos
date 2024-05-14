package com.example.demo.entities.transformations.Forma_pago;

import com.example.demo.entities.DTO.Forma_pagoDto;
import com.example.demo.entities.Forma_pago;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Forma_pagoMapper implements Function<Forma_pagoDto, Forma_pago> {
    @Override
    public Forma_pago apply(Forma_pagoDto formaPagoDto){
        return new Forma_pago(
                formaPagoDto.getId_pago(),
                formaPagoDto.getNombre()
        );
    }
}
