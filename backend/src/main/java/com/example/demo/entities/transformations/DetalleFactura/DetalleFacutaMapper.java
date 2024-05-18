package com.example.demo.entities.transformations.DetalleFactura;


import com.example.demo.entities.DTO.DetalleFacturaDto;
import com.example.demo.entities.Detallefactura;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DetalleFacutaMapper implements Function<DetalleFacturaDto, Detallefactura> {
    @Override
    public Detallefactura apply(DetalleFacturaDto detalleFacturaDto){
        return new Detallefactura(
                detalleFacturaDto.getId_detalle(),
                detalleFacturaDto.getFactura(),
                detalleFacturaDto.getCantidad(),
                detalleFacturaDto.getFecha_realizada(),
                detalleFacturaDto.getArticulo()
        );
    }

}
