package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @Column(name = "id_usuario")
    private Long usuario;

    @Column(name = "nombre")
    private String username;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "password")
    private String password;

    @Column(name = "gmail_usuario")
    private String gmail;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToMany
    @JoinTable(
            name = "usuario_articulo",  // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "usuario_id"),  // Columna en la tabla de unión que referencia a la entidad Usuario
            inverseJoinColumns = @JoinColumn(name = "articulo_id")  // Columna en la tabla de unión que referencia a la entidad Articulo
    )
    private List<Articulo> articulos = new ArrayList<>();

}
