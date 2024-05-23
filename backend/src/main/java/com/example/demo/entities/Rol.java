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
@Table(name = "rol")
public class Rol {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @TableGenerator(name = "rol", table = "sqlite_sequence",
                pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "id",
                initialValue = 1,allocationSize = 1)
        @Column(name = "id")
        private Long id;

        @Column(name = "nombre")
        private String nombre;



    }
