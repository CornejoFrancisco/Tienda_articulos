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

    @Column(name = "id_cliente")
    private Long cliente;

    @Column(name = "password")
    private String password;

    @Column(name = "gmail_usuario")
    private String gmail;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
            , inverseJoinColumns = @JoinColumn(name = "id_roles", referencedColumnName = "id"))
    private List<Rol> roles = new ArrayList<>();

}
