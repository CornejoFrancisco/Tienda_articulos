package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "factura", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "id",
            initialValue = 1,allocationSize = 1)
    @Column(name = "id_factura")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_formapago")
    private Forma_pago formaPago;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total")
    private int total;


    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

}
