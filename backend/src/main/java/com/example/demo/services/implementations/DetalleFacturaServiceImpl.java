package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.DetalleFacturaDto;
import com.example.demo.entities.Detallefactura;
import com.example.demo.entities.transformations.DetalleFactura.DetalleFacturaDtoMapper;
import com.example.demo.entities.transformations.DetalleFactura.DetalleFacutaMapper;
import com.example.demo.repositories.DetalleFacturaRepository;
import com.example.demo.services.Interfaces.DetalleFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Service
public class DetalleFacturaServiceImpl implements DetalleFacturaService {

    private DetalleFacturaRepository detalleFacturaRepository;
    private DetalleFacturaDtoMapper detalleFacturaDtoMapper;
    private DetalleFacutaMapper detalleFacutaMapper;

    public DetalleFacturaServiceImpl(DetalleFacturaRepository detalleFacturaRepository, DetalleFacturaDtoMapper detalleFacturaDtoMapper, DetalleFacutaMapper detalleFacutaMapper) {
        this.detalleFacturaRepository = detalleFacturaRepository;
        this.detalleFacturaDtoMapper = detalleFacturaDtoMapper;
        this.detalleFacutaMapper = detalleFacutaMapper;
    }

    @Override
    public ResponseEntity<String> add(DetalleFacturaDto entity) {
        Detallefactura detallefactura = new Detallefactura();
        detallefactura.setFactura(entity.getFactura());
        detallefactura.setCantidad(entity.getCantidad());
        detallefactura.setArticulo(entity.getArticulo());
        detalleFacturaRepository.save(detallefactura);
        return new ResponseEntity<>("Registro de categoria detalle factura", HttpStatus.OK);
    }

    @Override
    public DetalleFacturaDto getById(Long id) {
        Optional<Detallefactura> detallefactura = detalleFacturaRepository.findById(id);

        return detallefactura.map(detalleFacturaDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<DetalleFacturaDto> getAll() {
        List<Detallefactura> detallefacturas = detalleFacturaRepository.findAll();
        return detallefacturas
                .stream()
                .map(detalleFacturaDtoMapper)
                .toList();
    }

    @Override
    public DetalleFacturaDto delete(Long id) {
        Optional<Detallefactura> detallefactura = detalleFacturaRepository.findById(id);
        detallefactura.ifPresent(detalleFacturaRepository :: delete);
        return detallefactura.map(detalleFacturaDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(DetalleFacturaDto entity) {
        Optional<Detallefactura> detallefactura = Stream
                .of(entity)
                .map(detalleFacutaMapper)
                .findAny();
        detallefactura.ifPresent(detalleFacturaRepository :: save);
    }
}
