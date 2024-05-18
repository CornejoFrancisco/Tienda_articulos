package com.example.demo.entities.transformations.Factura;


import com.example.demo.entities.DTO.FacturaDto;
import com.example.demo.entities.Factura;
import com.example.demo.repositories.SucursalRespository;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class FacturaMapper implements Function<Factura, FacturaDto>{

    @Override
    public FacturaDto apply(Factura factura){
        return new FacturaDto(
                factura.getId(),
                factura.getFormaPago(),
                factura.getFecha(),
                factura.getTotal(),
                factura.getSucursal()
                );
    }
}
