package com.example.demo.services.implementations;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.DTO.ClienteDto;
import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.Factura;
import com.example.demo.entities.Usuario;
import com.example.demo.entities.transformations.Usuario.UsuarioDtoMapper;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.Interfaces.ClienteService;
import com.example.demo.services.Interfaces.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioDtoMapper usuarioDtoMapper;
    private UsuarioService usuarioService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioDtoMapper usuarioDtoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    @Override
    public void add(UsuarioDto entity) {
        boolean mail_usado = verificacion_mail(entity);
        if(!mail_usado) {
            Usuario usuario = new Usuario();
            usuario.setPassword(entity.getPassword());
            usuario.setGmail(entity.getGmail());
            usuario.setCliente(entity.getCliente());
            usuarioRepository.save(usuario);
        }
    }

    private boolean verificacion_mail(UsuarioDto entity) {
        String gmail = entity.getGmail();
        List<UsuarioDto> listausuarioDto = getAll();
        boolean mail_usado = false;
        for(int i = 0; i < listausuarioDto.size(); i ++ ){
            UsuarioDto usuarioDto = listausuarioDto.get(i);
            if(gmail.equals(usuarioDto.getGmail())){
                mail_usado = true;
                break;
            }
        }
        return mail_usado;
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
        boolean mail_usado = verificacion_mail(entity);
        if(!mail_usado){
            usuario.ifPresent(usuarioRepository :: save);
        }

    }
}
