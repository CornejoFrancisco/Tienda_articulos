package com.example.demo.services.implementations;

import com.example.demo.entities.Articulo;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.DTO.ArticuloDto;
import com.example.demo.entities.Unidad_medida;
import com.example.demo.entities.transformations.Articulo.ArticuloDtoMapper;
import com.example.demo.entities.transformations.Articulo.ArticuloMapper;
import com.example.demo.repositories.ArticuloRepository;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.UnidadMedidaRepository;
import com.example.demo.services.Interfaces.ArticuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Service
public class ArticuloServiceImpl implements ArticuloService {

    private CategoriaRepository categoriaRepository;
    private UnidadMedidaRepository unidadMedidaRepository;

    private ArticuloRepository articuloRepository;
    private ArticuloDtoMapper articuloDtoMapper;
    private ArticuloMapper articuloMapper;
    public ArticuloServiceImpl(CategoriaRepository categoriaRepository, UnidadMedidaRepository unidadMedidaRepository, ArticuloRepository articuloRepository, ArticuloDtoMapper articuloDtoMapper, ArticuloMapper articuloMapper) {
        this.categoriaRepository = categoriaRepository;
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.articuloRepository = articuloRepository;
        this.articuloDtoMapper = articuloDtoMapper;
        this.articuloMapper = articuloMapper;
    }

    @Override
    public ResponseEntity<String> add(ArticuloDto entity) {
        Articulo articulo = new Articulo();
        Categoria categoria = categoriaRepository.getReferenceById(entity.getCategoria());
        articulo.setCategoria(categoria);
        articulo.setNombre(entity.getNombre());
        articulo.setPrecio(entity.getPrecio());
        Unidad_medida unidad_medida = unidadMedidaRepository.getReferenceById(entity.getUnidad_medida());
        articulo.setUnidad_medida(unidad_medida);
        articuloRepository.save(articulo);
        return new ResponseEntity<>("Registro de articulo exitoso", HttpStatus.OK);
    }

    @Override
    public ArticuloDto getById(Long id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        return articulo.map(articuloDtoMapper).orElseThrow();
    }

    @Override
    public List<ArticuloDto> getAll() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos.stream().map(articuloDtoMapper).toList();
    }

    @Override
    public ArticuloDto delete(Long id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        articulo.ifPresent(articuloRepository :: delete);
        return articulo.map(articuloDtoMapper).orElseThrow();
    }

    @Override
    public void update(ArticuloDto entity) {
        Optional<Articulo> articulo = Stream.of(entity)
                .map(articuloMapper)
                .findAny();
        articulo.ifPresent(articuloRepository :: save);
    }
}
