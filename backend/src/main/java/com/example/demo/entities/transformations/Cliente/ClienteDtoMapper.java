package com.example.demo.entities.transformations.Cliente;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.DTO.ClienteDto;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteDtoMapper implements Function<Cliente, ClienteDto> {

    @Override
    public ClienteDto apply(Cliente cliente){
        return new ClienteDto(
                cliente.getId(),
                cliente.getApellido(),
                cliente.getNombre(),
                cliente.getDomicilio(),
                cliente.getSexo(),
                cliente.getFecha_nacimiento()

        );
    }

}
