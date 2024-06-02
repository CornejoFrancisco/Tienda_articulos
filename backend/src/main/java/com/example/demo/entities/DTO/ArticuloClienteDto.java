package com.example.demo.entities.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloClienteDto {
    private Long id_articulo;
    private Long id_usuario;
}
