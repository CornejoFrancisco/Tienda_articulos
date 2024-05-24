package com.example.demo.entities.transformations.Usuario;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
public class UsuarioDtoMapper implements Function<Usuario, UsuarioDto> {
    @Override
    public UsuarioDto apply(Usuario usuario){
        Rol rol = null;
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            rol = usuario.getRoles().get(0); // Toma el primer rol de la lista
        }

        return new UsuarioDto(
                usuario.getUsuario(),
                usuario.getCliente(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getGmail(),
                rol
        );
    }
}
