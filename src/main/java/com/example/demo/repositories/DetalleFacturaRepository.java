package com.example.demo.repositories;

import com.example.demo.entities.Detallefactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<Detallefactura, Long> {
}
