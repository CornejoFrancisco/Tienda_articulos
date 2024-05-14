package com.example.demo.entities.transformations.Categoria;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.DTO.CategoriaDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoriaDtoMapper implements Function<Categoria, CategoriaDto> {

    @Override
    public CategoriaDto apply(Categoria categoria){
        return new CategoriaDto(
                categoria.getId_categoria(),
                categoria.getNombre()
        );
    }
}
