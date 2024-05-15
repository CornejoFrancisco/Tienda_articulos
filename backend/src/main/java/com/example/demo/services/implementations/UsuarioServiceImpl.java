package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Factura;
import com.example.demo.entities.Usuario;
import com.example.demo.entities.transformations.Usuario.UsuarioDtoMapper;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.Interfaces.UsuarioService;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioDtoMapper usuarioDtoMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioDtoMapper usuarioDtoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    @Override
    public void add(UsuarioDto entity) {
        Usuario usuario = new Usuario();
        usuario.setCliente(entity.getCliente());
        usuario.setPassword(entity.getPassword());
        usuario.setGmail(entity.getGmail());
        usuario.setCliente(entity.getCliente());
        usuarioRepository.save(usuario);

    }

    @Override
    public UsuarioDto getById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(usuarioDtoMapper).orElseThrow();
    }

    @Override
    public List<UsuarioDto> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioDtoMapper).toList();
    }

    @Override
    public UsuarioDto delete(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.ifPresent(usuarioRepository :: delete);
        return usuario.map(usuarioDtoMapper).orElseThrow();
    }

    @Override
    public void update(UsuarioDto entity) {
        Optional<Usuario> usuario = usuarioRepository.findById(entity.getUsuario());
        usuario.ifPresent(usuarioRepository :: save);
    }
}
