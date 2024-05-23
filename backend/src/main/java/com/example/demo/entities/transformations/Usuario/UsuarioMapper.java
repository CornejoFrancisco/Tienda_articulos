package com.example.demo.entities.transformations.Usuario;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
@Service
public class UsuarioMapper implements Function<UsuarioDto, Usuario> {
    @Override
    public Usuario apply(UsuarioDto usuarioDto){

        List<Rol> roles = Collections.singletonList(usuarioDto.getRol());
        return new Usuario(
                usuarioDto.getUsuario(),
                usuarioDto.getNombre(),
                usuarioDto.getCliente(),
                usuarioDto.getPassword(),
                usuarioDto.getGmail(),
                roles
        );
    }
}