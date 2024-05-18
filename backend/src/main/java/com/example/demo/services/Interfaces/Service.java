package com.example.demo.services.Interfaces;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service <T, W>{

    void add(T entity);
    T getById(W id);
    List<T> getAll();
    T delete(W id);
    void update(T entity);

}
