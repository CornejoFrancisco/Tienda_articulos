package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.Unidad_medidaDto;
import com.example.demo.entities.Unidad_medida;
import com.example.demo.entities.transformations.Unidad_medida.Unidad_medidaDtoMapper;
import com.example.demo.repositories.UnidadMedidaRepository;
import com.example.demo.services.Interfaces.UnidadMedidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Unidad_medidaServiceImpl implements UnidadMedidaService {

    private UnidadMedidaRepository unidadMedidaRepository;
    private Unidad_medidaDtoMapper unidadMedidaDtoMapper;

    public Unidad_medidaServiceImpl(UnidadMedidaRepository unidadMedidaRepository, Unidad_medidaDtoMapper unidadMedidaDtoMapper) {
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.unidadMedidaDtoMapper = unidadMedidaDtoMapper;
    }

    @Override
    public ResponseEntity<String> add(Unidad_medidaDto entity) {
        Unidad_medida unidad_medida = new Unidad_medida();
        unidad_medida.setNombre(entity.getNombre());
        unidadMedidaRepository.save(unidad_medida);
        return new ResponseEntity<>("Registro de categoria unidad medida", HttpStatus.OK);
    }

    @Override
    public Unidad_medidaDto getById(Long id) {

        Optional<Unidad_medida> unidad_medida = unidadMedidaRepository.findById(id);
        return unidad_medida.map(unidadMedidaDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<Unidad_medidaDto> getAll() {
        List<Unidad_medida> unidad_medida = unidadMedidaRepository.findAll();
        return unidad_medida.stream().map(unidadMedidaDtoMapper).toList();
    }

    @Override
    public Unidad_medidaDto delete(Long id) {

        Optional<Unidad_medida> unidad_medida = unidadMedidaRepository.findById(id);
        unidad_medida.ifPresent(unidadMedidaRepository :: delete);
        return unidad_medida.map(unidadMedidaDtoMapper).orElseThrow();
    }

    @Override
    public void update(Unidad_medidaDto entity) {
        Optional<Unidad_medida> unidad_medida = unidadMedidaRepository.findById(entity.getId_unidad_medida());

        unidad_medida.ifPresent(unidad_medida1 ->
        {
            unidad_medida1.setNombre(entity.getNombre());
            unidadMedidaRepository.save(unidad_medida1);
        });
    }
}
