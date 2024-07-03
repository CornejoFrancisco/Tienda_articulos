package com.example.demo.entities.transformations.Sucursal;

import com.example.demo.entities.DTO.SucursalDto;
import com.example.demo.entities.Sucursal;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SucursalMapper implements Function<SucursalDto, Sucursal> {

    @Override
    public Sucursal apply(SucursalDto sucursalDto){
        return new Sucursal(
                sucursalDto.getId(),
                sucursalDto.getNombre()
        );
    }
}
