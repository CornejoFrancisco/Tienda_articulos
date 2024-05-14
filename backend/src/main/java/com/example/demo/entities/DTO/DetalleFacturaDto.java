package com.example.demo.entities.DTO;



import com.example.demo.entities.Articulo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaDto {
    private Long id_detalle;
    private Long factura;
    private int cantidad;
    private Articulo articulo;
}
