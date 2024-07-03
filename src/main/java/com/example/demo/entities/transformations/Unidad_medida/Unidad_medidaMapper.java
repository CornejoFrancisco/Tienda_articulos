package com.example.demo.entities.transformations.Unidad_medida;

import com.example.demo.entities.DTO.Unidad_medidaDto;
import com.example.demo.entities.Unidad_medida;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Unidad_medidaMapper implements Function<Unidad_medidaDto, Unidad_medida> {
    @Override
    public Unidad_medida apply(Unidad_medidaDto unidad_medida){
        return new Unidad_medida(
                unidad_medida.getId_unidad_medida(),
                unidad_medida.getNombre()
        );
    }
}
