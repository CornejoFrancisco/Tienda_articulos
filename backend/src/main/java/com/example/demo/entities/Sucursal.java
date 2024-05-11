package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sucursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "sucursal", table = "sqlite_sequence",
    pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "id",
    initialValue = 1,allocationSize = 1)
    @Column(name = "id_sucursal")
    private Long id;

    @Column(name = "nombre")
    private char nombre;

    @OneToMany(mappedBy = "sucursal")
    private List<Factura> facturas;

}
