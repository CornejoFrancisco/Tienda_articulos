package com.example.demo.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoAdmin {
    private Long usuario;
    private Long cliente;
    private String username;
    private String password;
    private String gmail;
}
