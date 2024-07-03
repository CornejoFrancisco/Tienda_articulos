package com.example.demo.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class UsuarioArticuloKey implements Serializable {
    private Long usuarioId;
    private Long articuloId;

    // Getters, setters, equals, hashCode
}
