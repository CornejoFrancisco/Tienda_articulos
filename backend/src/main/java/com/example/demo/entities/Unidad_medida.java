package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unidad_medida")
public class Unidad_medida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "unidad_medida", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "id",
            initialValue = 1,allocationSize = 1)
    @Column(name = "id_unidad_medida")
    private Long id_unidad_medida;

    @Column(name = "nombre")
    private String nombre;



}
