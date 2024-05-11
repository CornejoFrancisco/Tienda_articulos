package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "usuario", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "id",
            initialValue = 1,allocationSize = 1)
    @Column(name = "idUsuario")
    private Long usuario;

    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "password")
    private String password;

    @Column(name = "gmail_usuario")
    private String gmail;

}