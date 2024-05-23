package com.example.demo.services.Interfaces;

import com.example.demo.entities.DTO.FacturaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


@org.springframework.stereotype.Service
public interface FacturaService{
    public List<FacturaDto> getAll();
    public FacturaDto delete(Long id);
    public FacturaDto getById(Long id);
    public ResponseEntity<String> add(FacturaDto entity);

}
