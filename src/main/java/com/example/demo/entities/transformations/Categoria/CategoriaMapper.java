package com.example.demo.entities.transformations.Categoria;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.DTO.CategoriaDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoriaMapper implements Function<CategoriaDto, Categoria> {
    @Override
    public Categoria apply(CategoriaDto categoriaDto){
        return new Categoria(
                categoriaDto.getId_categoria(),
                categoriaDto.getNombre()
        );
    }
}
