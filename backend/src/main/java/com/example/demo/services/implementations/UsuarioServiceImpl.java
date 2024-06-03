package com.example.demo.services.implementations;

import com.example.demo.entities.DTO.UsuarioDto;
import com.example.demo.entities.DTO.UsuarioDtoAdmin;
import com.example.demo.entities.DTO.UsuarioDtoUser;
import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.entities.transformations.Usuario.UsuarioDtoMapper;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.Interfaces.UsuarioService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioDtoMapper usuarioDtoMapper;
    private PasswordEncoder passwordEncoder;

    private RolRepository rolRepository;
    private ClienteRepository   clienteRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioDtoMapper usuarioDtoMapper, PasswordEncoder passwordEncoder, RolRepository rolRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ResponseEntity<String> addUser(UsuarioDtoUser entity) {

        Optional<Usuario> user_gmail = usuarioRepository.findUsuarioByGmail(entity.getGmail());
        Optional<Usuario> user_username = usuarioRepository.findByUsername(entity.getUsername());
        if (user_username.isPresent() || user_gmail.isPresent()) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
            Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode(entity.getPassword()));
        usuario.setUsername(entity.getUsername());
        usuario.setGmail(entity.getGmail());
        usuario.setCliente(clienteRepository.getReferenceById(entity.getCliente()));

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
    public UsuarioDto delete(Long id) throws UserPrincipalNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setUsername(usuarioOptional.get().getUsername());
            usuario.setPassword(usuarioOptional.get().getPassword());
            usuario.setGmail(usuarioOptional.get().getGmail());
            usuario.setUsuario(usuarioOptional.get().getUsuario());
            usuario.setRol(usuarioOptional.get().getRol());
            usuario.setArticulos(usuarioOptional.get().getArticulos());
            usuario.setCliente(usuarioOptional.get().getCliente());
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            return usuarioDtoMapper.apply(usuario);
        } else {
            throw new UserPrincipalNotFoundException("Usuario no encontrado");
        }
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

        Optional<Usuario> user_gmail = usuarioRepository.findUsuarioByGmail(entity.getGmail());
        Optional<Usuario> user_username = usuarioRepository.findByUsername(entity.getUsername());
        if (!user_username.isPresent() || !user_gmail.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setUsername(entity.getUsername());
            usuario.setPassword(passwordEncoder.encode(entity.getPassword()));
            usuario.setGmail(entity.getGmail());
            usuario.setCliente(clienteRepository.getReferenceById(entity.getCliente()));
            Rol roles = rolRepository.findByName("ADMI").get();
            usuario.setRol(roles);
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("El usuario creado", HttpStatus.BAD_REQUEST);

        }else {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
    }







}
