package com.example.demo.entities.transformations.Cliente;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.DTO.ClienteDto;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ClienteMapper implements Function<ClienteDto, Cliente> {

    private UsuarioRepository usuarioRepository;

    @Override
    public Cliente apply(ClienteDto clienteDto){
        return new Cliente(
                clienteDto.getId(),
                clienteDto.getApellido(),
                clienteDto.getNombre(),
                clienteDto.getDomicilio(),
                clienteDto.getSexo(),
                clienteDto.getFecha_nacimiento(),
                usuarioRepository.getReferenceById(clienteDto.getUsuario())
        );
    }
}
