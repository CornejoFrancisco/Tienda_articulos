package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.SucursalDto;
import com.example.demo.entities.Factura;
import com.example.demo.entities.Sucursal;
import com.example.demo.entities.Unidad_medida;
import com.example.demo.entities.transformations.Sucursal.SucursalDtoMapper;
import com.example.demo.repositories.SucursalRespository;
import com.example.demo.services.Interfaces.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SucursalServiceImpl implements SucursalService {

    private SucursalRespository sucursalRespository;
    private SucursalDtoMapper sucursalDtoMapper;

    public SucursalServiceImpl(SucursalRespository sucursalRespository, SucursalDtoMapper sucursalDtoMapper) {
        this.sucursalRespository = sucursalRespository;
        this.sucursalDtoMapper = sucursalDtoMapper;
    }

    @Override
    public ResponseEntity<String> add(SucursalDto entity) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(entity.getNombre());
        sucursalRespository.save(sucursal);
        return new ResponseEntity<>("Registro de categoria sucursal", HttpStatus.OK);
    }

    @Override
    public SucursalDto getById(Long id) {
        Optional<Sucursal> sucursal = sucursalRespository.findById(id);
        return sucursal.map(sucursalDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<SucursalDto> getAll() {
        List<Sucursal> sucursal = sucursalRespository.findAll();
        return sucursal.stream().map(sucursalDtoMapper).toList();
    }

    @Override
    public SucursalDto delete(Long id) {
        Optional<Sucursal> sucursal = sucursalRespository.findById(id);
        sucursal.ifPresent(sucursalRespository :: delete);
        return sucursal.map(sucursalDtoMapper).orElseThrow();
    }

    @Override
    public void update(SucursalDto entity) {
        Optional<Sucursal> sucursal = sucursalRespository.findById(entity.getId());
        sucursal.ifPresent(sucursal1 -> {
            sucursal1.setNombre(entity.getNombre());
            sucursalRespository.save(sucursal1);
        });
    }
}
