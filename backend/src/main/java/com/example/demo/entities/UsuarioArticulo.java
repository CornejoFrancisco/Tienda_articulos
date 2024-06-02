package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_articulo")
public class UsuarioArticulo {

    @EmbeddedId
    private UsuarioArticuloKey id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToOne
    @MapsId("articuloId")
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;
}
