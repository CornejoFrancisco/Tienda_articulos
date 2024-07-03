package com.example.demo.entities.DTO;


import com.example.demo.entities.Detallefactura;
import com.example.demo.entities.Forma_pago;
import com.example.demo.entities.Sucursal;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {
    private Long id;

    private Forma_pago formaPago;

    private Date fecha;
    private int total;

    private Sucursal sucursal;
}
