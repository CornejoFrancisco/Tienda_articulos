package com.example.demo.entities.DTO;


import com.example.demo.entities.Cliente;
import com.example.demo.entities.Factura;
import com.example.demo.entities.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long usuario;
    private Long cliente;
    private String nombre;
    private String password;
    private String gmail;
    private Rol rol;

}
