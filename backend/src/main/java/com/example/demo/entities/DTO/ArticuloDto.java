package com.example.demo.entities.DTO;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Unidad_medida;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticuloDto {
    private Long id_articulo;
    private String nombre;
    private String precio;
    private Long categoria;
    private Long unidad_medida;
}
