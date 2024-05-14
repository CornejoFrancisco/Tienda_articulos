package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalle_factura")
@Entity
public class Detallefactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "detalle_factura", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "id",
            initialValue = 1,allocationSize = 1)
    @Column(name = "id_detalle")
    private Long id_detalle;

    @Column(name = "factura")
    private Long factura;

    @Column(name = "cantidad")
    private int cantidad;


}
