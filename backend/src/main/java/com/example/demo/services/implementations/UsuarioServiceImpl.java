package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.DTO.UsuarioDtoAdmin;
import com.example.demo.entities.DTO.UsuarioDtoUser;
import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.entities.transformations.Usuario.UsuarioDtoMapper;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.Interfaces.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioDtoMapper usuarioDtoMapper;
    private PasswordEncoder passwordEncoder;

    private RolRepository rolRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioDtoMapper usuarioDtoMapper, PasswordEncoder passwordEncoder, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
    }

    @Override
    public ResponseEntity<String> addUser(UsuarioDtoUser entity) {

        if (findByUsername(entity.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        boolean mail_usado = verificacion_mail(entity.getGmail());
        if(mail_usado) {
            return new ResponseEntity<>("el usuario con ese mail, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode(entity.getPassword()));
        usuario.setUsername(entity.getUsername());
        usuario.setGmail(entity.getGmail());
        usuario.setCliente(entity.getCliente());

        Rol roles = rolRepository.findByName("USER").get();
        usuario.setRol(roles);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("el usuario creado", HttpStatus.BAD_REQUEST);
    }

    private boolean verificacion_mail(String gmail) {
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
    public ResponseEntity<String> add(UsuarioDto entity) {
        return null;
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
        boolean mail_usado = verificacion_mail(entity.getGmail());
        if(!mail_usado){
            usuario.ifPresent(usuarioRepository :: save);
        }

    }

    @Override
    public ResponseEntity<String> addAdmi(UsuarioDtoAdmin entity) {

        if (findByUsername(entity.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        boolean mail_usado = verificacion_mail(entity.getGmail());
        if(mail_usado) {
            return new ResponseEntity<>("el usuario con ese mail, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(entity.getUsername());
        usuario.setPassword(passwordEncoder.encode(entity.getPassword()));
        usuario.setGmail(entity.getGmail());
        usuario.setCliente(entity.getCliente());
        Rol roles = rolRepository.findByName("ADMI").get();
        usuario.setRol(roles);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("El usuario creado", HttpStatus.BAD_REQUEST);
    }



    public boolean findByUsername(String username) {
        List<Usuario> list_usuario = usuarioRepository.findAll();
        boolean usuario = verificacion_usuario(list_usuario, username);
        return usuario;
    }



    private boolean verificacion_usuario(List<Usuario> usuarios, String name) {
        Usuario usuario_encontrado = new Usuario();
        boolean encontrardo = false;
        for(int i = 1; i < usuarios.size(); i ++ ){
            usuario_encontrado = usuarios.get(i);
            if(usuario_encontrado.getUsername().equals(name)){
                encontrardo = true;
                return encontrardo;
            }
        }
        return encontrardo;

    }
}
