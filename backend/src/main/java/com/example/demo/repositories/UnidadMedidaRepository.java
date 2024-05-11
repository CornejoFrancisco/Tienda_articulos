package com.example.demo.repositories;

import com.example.demo.entities.Unidad_medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<Unidad_medida, Long> {
}
