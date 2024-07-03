package com.example.demo.entities.DTO;

import com.example.demo.entities.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoUser {
    private Long usuario;
    private Long cliente;
    private String username;
    private String password;
    private String gmail;
}
