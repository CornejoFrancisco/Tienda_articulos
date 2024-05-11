package com.example.demo.entities.DTO;



import com.example.demo.entities.Articulo;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
    private Long id_categoria;

    private String nombre;
    private List<Long> articulos;
}
