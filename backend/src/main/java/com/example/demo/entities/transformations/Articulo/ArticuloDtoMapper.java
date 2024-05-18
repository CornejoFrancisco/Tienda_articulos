package com.example.demo.entities.transformations.Articulo;

import com.example.demo.entities.Articulo;
import com.example.demo.entities.DTO.ArticuloDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArticuloDtoMapper implements Function<Articulo, ArticuloDto> {

    @Override
    public ArticuloDto apply(Articulo articulo){
        return new ArticuloDto(
                articulo.getIdArticulo(),
                articulo.getNombre(),
                articulo.getPrecio(),
                articulo.getCategoria().getId_categoria(),
                articulo.getUnidad_medida().getId_unidad_medida()
                );

    }

}
