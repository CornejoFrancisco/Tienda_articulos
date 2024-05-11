package com.example.demo.repositories;

import com.example.demo.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRespository extends JpaRepository<Sucursal, Long> {
}
