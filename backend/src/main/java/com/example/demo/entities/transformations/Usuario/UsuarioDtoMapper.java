package com.example.demo.entities.transformations.Usuario;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Usuario;
import org.springframework.hateoas.server.core.WebHandler;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UsuarioDtoMapper implements Function<Usuario, UsuarioDto> {
    @Override
    public UsuarioDto apply(Usuario usuario){
        return new UsuarioDto(
                usuario.getUsuario(),
                usuario.getCliente(),
                usuario.getPassword(),
                usuario.getGmail()
        );
    }
}
