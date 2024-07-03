package com.example.demo.repositories;

import com.example.demo.entities.Articulo;
import com.example.demo.entities.DTO.ArticuloClienteDto;
import com.example.demo.entities.DTO.ArticuloDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    List<Articulo> findAllByCategoria(Long categoria);
    List<Articulo> findArticuloByNombre(String nombre);

}
