package com.example.demo.entities.transformations.Sucursal;

import com.example.demo.entities.DTO.SucursalDto;
import com.example.demo.entities.Sucursal;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SucursalDtoMapper implements Function<Sucursal, SucursalDto> {
    @Override
    public SucursalDto apply(Sucursal sucursal){
        return new SucursalDto(
                sucursal.getId(),
                sucursal.getNombre()
        );
    }
}
