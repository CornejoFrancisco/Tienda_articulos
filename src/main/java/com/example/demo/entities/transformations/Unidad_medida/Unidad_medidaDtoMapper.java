package com.example.demo.entities.transformations.Unidad_medida;

import com.example.demo.entities.DTO.Unidad_medidaDto;
import com.example.demo.entities.Unidad_medida;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Unidad_medidaDtoMapper implements Function<Unidad_medida, Unidad_medidaDto> {
    @Override
    public Unidad_medidaDto apply(Unidad_medida unidad_medida){
        return new Unidad_medidaDto(
                unidad_medida.getId_unidad_medida(),
                unidad_medida.getNombre()
        );
    }
}
