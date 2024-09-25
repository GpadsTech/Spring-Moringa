package com.gpads.moringa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.repositories.PlacaOutPutRepositoryMongoDB;

@Service
public class PlacaOutPutService {
    @Autowired
    PlacaOutPutRepositoryMongoDB placaOutPutRepositoryMongoDB;

    public PlacaOutPut findLatestByPlacaId(Long id){
        return placaOutPutRepositoryMongoDB.findLatestByPlacaId(id).get(0);
    }

    public List<PlacaOutPut> findByPlacaIdAndDateRange(Long id, Date startDate, Date endDate){
        return placaOutPutRepositoryMongoDB.findByPlacaIdAndDateRange(id, startDate, endDate);
    }
}
