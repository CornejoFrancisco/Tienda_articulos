package com.example.demo.services.Interfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service <T, W>{

    ResponseEntity<String> add(T entity);
    T getById(W id);
    List<T> getAll();
    T delete(W id);
    void update(T entity);

}
