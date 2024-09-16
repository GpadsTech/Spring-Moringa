package com.gpads.moringa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.repositories.PlacaOutPutRepository;
import com.gpads.moringa.repositories.PlacaRepository;

import java.util.Date;


@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
	PlacaRepository placaRepository;

	@Autowired
	PlacaOutPutRepository placaOutPutRepository;

    @RequestMapping("/findLatestDataByPlacaId/{idPlaca}")
    public ResponseEntity<PlacaOutPut> lastData(@PathVariable("idPlaca") Long idPlaca) {
        PlacaOutPut resource = placaOutPutRepository.findLatestByPlacaId(idPlaca);
        if(resource != null)
            return ResponseEntity.ok(resource);

        return ResponseEntity.notFound().build();
    }
    








    public void initialData(){
		Placa p = new Placa();
		placaRepository.save(p);
		PlacaOutPut pOut = new PlacaOutPut();
		pOut.setDataHora(new Date());
		pOut.setLuminosidade(30000);
		pOut.setPlaca(p);
		pOut.setPressao(950);
		pOut.setQualidadeDoAr(320);
		pOut.setRpm(220);
		pOut.setTemperatura(35);
		pOut.setUmidade(50);
		pOut.setVelocidadeDoVento(5);
		pOut.setVoltagem(0.5F);
		pOut.setcO2(11.7F);
		placaOutPutRepository.save(pOut);
	}

    
    
}
