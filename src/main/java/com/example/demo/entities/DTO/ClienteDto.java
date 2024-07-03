package com.example.demo.entities.DTO;



import com.example.demo.entities.Cliente;
import com.example.demo.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long id;
    private String apellido;
    private String nombre;
    private String domicilio;
    private String sexo;
    private Date fecha_nacimiento;
}
