package com.example.demo.entities.transformations.Usuario;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
public class UsuarioMapper implements Function<UsuarioDto, Usuario> {

    private RolRepository rolRepository;

    public UsuarioMapper(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario apply(UsuarioDto usuarioDto){

        Optional<Rol> rolOptional = rolRepository.findById(usuarioDto.getRol().getId());
        Rol rol = rolOptional.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        return new Usuario(
                usuarioDto.getUsuario(),
                usuarioDto.getUsername(),
                usuarioDto.getCliente(),
                usuarioDto.getPassword(),
                usuarioDto.getGmail(),
                rol
        );
    }
}