package com.example.demo.repositories;

import com.example.demo.entities.DTO.ArticuloClienteDto;
import com.example.demo.entities.UsuarioArticuloKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioArticuloRepository extends JpaRepository<ArticuloClienteDto, UsuarioArticuloKey> {
}

