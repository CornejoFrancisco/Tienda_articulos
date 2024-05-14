package com.example.demo.entities.transformations.Factura;


import com.example.demo.entities.DTO.FacturaDto;
import com.example.demo.entities.Factura;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FacturaDtoMapper implements Function<Factura, FacturaDto> {
    @Override
    public FacturaDto apply(Factura factura){
        return new FacturaDto(
                factura.getId(),
                factura.getNumero(),
                factura.getFormaPago(),
                factura.getFecha(),
                factura.getTotal(),
                factura.getDetalles(),
                factura.getSucursal()
        );
    }

}
