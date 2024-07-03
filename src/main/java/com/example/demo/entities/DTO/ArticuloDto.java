package com.example.demo.entities.DTO;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Unidad_medida;
import com.example.demo.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticuloDto {
    private Long id_articulo;
    private String nombre;
    private int precio;
    private Long categoria;
    private Long unidad_medida;
    private int stock;
    private String descripcion;
    private int me_gusta;
    private Set<Usuario> usuarios;





}
