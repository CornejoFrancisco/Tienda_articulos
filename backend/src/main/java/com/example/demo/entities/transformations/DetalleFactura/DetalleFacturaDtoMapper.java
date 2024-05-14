package com.example.demo.entities.transformations.DetalleFactura;

import com.example.demo.entities.DTO.DetalleFacturaDto;
import com.example.demo.entities.Detallefactura;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DetalleFacturaDtoMapper implements Function<Detallefactura, DetalleFacturaDto> {
    @Override
    public DetalleFacturaDto apply(Detallefactura detallefactura){
        return new DetalleFacturaDto(
                detallefactura.getId_detalle(),
                detallefactura.getFactura(),
                detallefactura.getCantidad()
                );
    }
}
