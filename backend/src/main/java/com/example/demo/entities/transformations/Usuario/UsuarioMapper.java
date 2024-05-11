package com.example.demo.entities.transformations.Usuario;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UsuarioMapper implements Function<UsuarioDto, Usuario> {
    @Override
    public Usuario apply(UsuarioDto usuarioDto){
        return new Usuario(
                usuarioDto.getUsuario(),
                usuarioDto.getCliente(),
                usuarioDto.getPassword(),
                usuarioDto.getGmail()
        );
    }
}