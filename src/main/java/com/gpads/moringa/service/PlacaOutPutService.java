package com.gpads.moringa.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.repositories.PlacaOutPutRepositoryMongoDB;

@Service
public class PlacaOutPutService {

    @Autowired
    PlacaOutPutRepositoryMongoDB placaOutPutRepositoryMongoDB;

    @Autowired
    PlacaService placaService;

    public PlacaOutPut findLatestByPlacaId(ObjectId id) {
        return placaOutPutRepositoryMongoDB.findLatestByPlacaId(id).get(0);
    }

    public List<PlacaOutPut> findByPlacaIdAndDateRange(ObjectId id, Date startDate, Date endDate) {
        return placaOutPutRepositoryMongoDB.findByPlacaIdAndDateRange(id, startDate, endDate);
    }
    public List<PlacaOutPut> findAll(){
        return placaOutPutRepositoryMongoDB.findAll();
    }

    public void gerarLeituras(Date startDate, Date endDate) {
        List<Placa> placas = placaService.findAll(); 
        Random random = new Random();

        for (Placa placa : placas) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
                float temperatura = gerarValorAleatorio(15.0f, 35.0f, random); 
                float umidade = gerarValorAleatorio(20.0f, 80.0f, random);     
                float pressao = gerarValorAleatorio(950.0f, 1050.0f, random);  
                float luminosidade = gerarValorAleatorio(0.0f, 1000.0f, random); 
                float cO2 = gerarValorAleatorio(300.0f, 500.0f, random);        
                float qualidadeDoAr = gerarValorAleatorio(0.0f, 500.0f, random); 
                float velocidadeDoVento = gerarValorAleatorio(0.0f, 20.0f, random); 
                float voltagem = gerarValorAleatorio(0.0f, 0.1f, random);   
                float rpm = gerarValorAleatorio(0.0f, 5000.0f, random);         

                PlacaOutPut placaOutPut = new PlacaOutPut(
                        calendar.getTime(),
                        temperatura,
                        umidade,
                        pressao,
                        luminosidade,
                        cO2,
                        qualidadeDoAr,
                        velocidadeDoVento,
                        voltagem, 
                        rpm,
                        placa
                );

                placaOutPutRepositoryMongoDB.save(placaOutPut);

                calendar.add(Calendar.HOUR, 1);
            }
        }

    }
    private float gerarValorAleatorio(float min, float max, Random random) {
        return min + random.nextFloat() * (max - min);
    }
}
