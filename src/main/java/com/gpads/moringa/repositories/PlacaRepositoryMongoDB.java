package com.gpads.moringa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gpads.moringa.entities.Placa;

public interface PlacaRepositoryMongoDB extends MongoRepository<Placa, Long>{
    
}
