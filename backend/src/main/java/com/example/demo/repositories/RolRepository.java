package com.example.demo.repositories;

import com.example.demo.entities.Articulo;
import com.example.demo.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Articulo, Long>{
    Optional<Rol> findByName(String name);
}
