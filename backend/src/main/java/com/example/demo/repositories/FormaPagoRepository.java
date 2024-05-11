package com.example.demo.repositories;

import com.example.demo.entities.Forma_pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoRepository extends JpaRepository<Forma_pago, Long> {
}
