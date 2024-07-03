package com.example.demo.entities.DTO;



import com.example.demo.entities.Factura;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forma_pagoDto {
    private Long id_pago;

    private String nombre;

}
