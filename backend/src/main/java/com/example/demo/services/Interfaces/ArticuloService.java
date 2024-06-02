package com.example.demo.services.Interfaces;

import com.example.demo.entities.DTO.ArticuloClienteDto;
import com.example.demo.entities.DTO.ArticuloDto;

@org.springframework.stereotype.Service
public interface ArticuloService extends Service<ArticuloDto, Long> {

    void addMegusta(ArticuloClienteDto articuloClienteDto);


}
