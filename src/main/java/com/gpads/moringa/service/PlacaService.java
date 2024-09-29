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
    public void save(Placa entity){
        placaRepositoryMongoDB.save(entity);
    }
    public void criarPlacas() {
        Placa placa1 = new Placa("GPADS-MoRHInGa AM1", -7.9917f, -38.2981f);  // Serra Talhada
        Placa placa2 = new Placa("GPADS-MoRHInGa AM1", -8.2845f, -35.9691f);  // Caruaru
        Placa placa3 = new Placa("GPADS-MoRHInGa AM1", -9.3887f, -40.5027f);  // Petrolina
        Placa placa4 = new Placa("GPADS-MoRHInGa AM1", -8.8823f, -36.4968f);  // Garanhuns
        Placa placa5 = new Placa("GPADS-MoRHInGa AM1", -8.0476f, -34.8770f);  // Recife 
    
        placaRepositoryMongoDB.save(placa1);
        placaRepositoryMongoDB.save(placa2);
        placaRepositoryMongoDB.save(placa3);
        placaRepositoryMongoDB.save(placa4);
        placaRepositoryMongoDB.save(placa5); 
    }
    
}
