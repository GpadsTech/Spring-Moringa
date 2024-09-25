package com.gpads.moringa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.repositories.PlacaRepositoryMongoDB;

@Service
public class PlacaService {
    @Autowired
    private PlacaRepositoryMongoDB placaRepositoryMongoDB;

    public List<Placa> findAll(){
        return placaRepositoryMongoDB.findAll();
    }
}
