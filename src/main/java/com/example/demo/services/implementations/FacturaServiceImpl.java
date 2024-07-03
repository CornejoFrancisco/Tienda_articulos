package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.FacturaDto;
import com.example.demo.entities.Factura;
import com.example.demo.entities.transformations.Factura.FacturaDtoMapper;
import com.example.demo.entities.transformations.Factura.FacturaMapper;
import com.example.demo.repositories.FacturaRepository;
import com.example.demo.services.Interfaces.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class FacturaServiceImpl implements FacturaService {

    private FacturaRepository facturaRepository;
    private FacturaDtoMapper facturaDtoMapper;


    public FacturaServiceImpl(FacturaRepository facturaRepository, FacturaDtoMapper facturaDtoMapper, FacturaMapper facturaMapper) {
        this.facturaRepository = facturaRepository;
        this.facturaDtoMapper = facturaDtoMapper;

    }

    @Override

    public ResponseEntity<String> add(FacturaDto entity) {
        Factura factura = new Factura();
        Date date = new Date();
        factura.setFecha(date);
        factura.setSucursal(entity.getSucursal());
        factura.setFormaPago(entity.getFormaPago());
        facturaRepository.save(factura);
        return new ResponseEntity<>("Registro de categoria factura", HttpStatus.OK);
    }

    @Override

    public FacturaDto getById(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.map(facturaDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<FacturaDto> getAll() {
        List<Factura> facturas = facturaRepository.findAll();

        return facturas.stream().map(facturaDtoMapper).toList();
    }

    @Override

    public FacturaDto delete(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        factura.ifPresent(facturaRepository :: delete);
        return factura.map(facturaDtoMapper).orElseThrow();
    }




}
