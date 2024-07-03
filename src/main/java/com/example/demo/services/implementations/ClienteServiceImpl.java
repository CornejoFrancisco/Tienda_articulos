package com.example.demo.services.implementations;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.DTO.ClienteDto;
import com.example.demo.entities.transformations.Cliente.ClienteDtoMapper;
import com.example.demo.entities.transformations.Cliente.ClienteMapper;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.services.Interfaces.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private ClienteDtoMapper clienteDtoMapper;
    private ClienteMapper clienteMapper;


    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteDtoMapper clienteDtoMapper, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteDtoMapper = clienteDtoMapper;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ResponseEntity<String> add(ClienteDto entity) {
        Cliente cliente = new Cliente();
        cliente.setApellido(entity.getApellido());
        cliente.setSexo(entity.getSexo());
        cliente.setDomicilio(entity.getDomicilio());
        cliente.setNombre(entity.getNombre());
        Date fecha_actual = new Date();
        cliente.setFecha_nacimiento(fecha_actual);
        clienteRepository.save(cliente);
        return new ResponseEntity<>("Registro de categoria cliente", HttpStatus.OK);

    }

    @Override
    public ClienteDto getById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(clienteDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<ClienteDto> getAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes
                .stream().map(clienteDtoMapper).toList();
    }

    @Override
    public ClienteDto delete(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        cliente.ifPresent(clienteRepository :: delete);
        return cliente.map(clienteDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(ClienteDto entity) {
        Optional<Cliente> cliente = clienteRepository.findById(entity.getId());
        cliente.ifPresent(clienteRepository :: save);
    }
}
