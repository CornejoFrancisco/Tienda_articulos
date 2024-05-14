package com.example.demo.services.implementations;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.DTO.CategoriaDto;
import com.example.demo.entities.transformations.Categoria.CategoriaDtoMapper;
import com.example.demo.entities.transformations.Categoria.CategoriaMapper;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.services.Interfaces.CategoriaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;
    private CategoriaDtoMapper categoriaDtoMapper;
    private CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaDtoMapper categoriaDtoMapper, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaDtoMapper = categoriaDtoMapper;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public void add(CategoriaDto entity) {
        Categoria categoria = new Categoria();
        categoria.setNombre(entity.getNombre());
        categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaDto getById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria
                .map(categoriaDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<CategoriaDto> getAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias
                .stream().map(categoriaDtoMapper).toList();
    }

    @Override
    public CategoriaDto delete(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        categoria.ifPresent(categoriaRepository :: delete);
        return categoria
                .map(categoriaDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(CategoriaDto entity) {
        Optional<Categoria> categoria = Stream
                .of(entity)
                .map(categoriaMapper)
                .findAny();
        categoria.ifPresent(categoriaRepository :: save);
    }
}
