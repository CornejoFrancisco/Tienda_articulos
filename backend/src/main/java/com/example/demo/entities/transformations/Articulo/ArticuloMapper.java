package com.example.demo.entities.transformations.Articulo;

import com.example.demo.entities.Articulo;
import com.example.demo.entities.DTO.ArticuloDto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.UnidadMedidaRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArticuloMapper implements Function<ArticuloDto, Articulo> {

    public UnidadMedidaRepository unidadMedidaRepository;
    public CategoriaRepository categoriaRepository;

    @Override
    public Articulo apply(ArticuloDto articuloDto){
        return new Articulo(
                articuloDto.getId_articulo(),
                articuloDto.getNombre(),
                articuloDto.getPrecio(),
                categoriaRepository.getReferenceById(articuloDto.getCategoria()),
                unidadMedidaRepository.getReferenceById(articuloDto.getUnidad_medida())
        );
    }
}
