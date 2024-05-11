package com.example.demo.entities.DTO;


import com.example.demo.entities.Cliente;
import com.example.demo.entities.Factura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long usuario;
    private Cliente cliente;
    private String password;
    private String gmail;

}
