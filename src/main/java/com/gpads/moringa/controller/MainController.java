package com.gpads.moringa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.repositories.PlacaOutPutRepositoryMongoDB;
import com.gpads.moringa.repositories.PlacaRepositoryMongoDB;
import com.gpads.moringa.service.PlacaOutPutService;
import com.gpads.moringa.service.PlacaService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MainController {

	

	@Autowired
	private PlacaOutPutService placaOutPutService;

	@Autowired
	private PlacaService placaService;
	

	@RequestMapping("/findLatestDataByPlacaId/{idPlaca}")
	public ResponseEntity<PlacaOutPut> lastData(@PathVariable("idPlaca") Long idPlaca) {

		//List<PlacaOutPut> resource = placaOutPutRepository.findLatestByPlacaId(idPlaca);
		PlacaOutPut resource = placaOutPutService.findLatestByPlacaId(idPlaca);
		

		if (resource != null)
			return ResponseEntity.ok(resource);

		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/findByPlacaIdAndDateRange/{idPlaca}/{startDate}/{endDate}")
	public ResponseEntity<List<PlacaOutPut>> findByPlacaIdAndDateRange(@PathVariable("idPlaca") Long idPlaca,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {

		Date starD = Date.from(Instant.parse(startDate));
		Date endD = Date.from(Instant.parse(endDate));

		//List<PlacaOutPut> resourceList = placaOutPutRepository.findByPlacaIdAndDateRange(idPlaca, starD, endD);
		List<PlacaOutPut> resourceList = placaOutPutService.findByPlacaIdAndDateRange(idPlaca, starD, endD);

		if (resourceList != null) {
			return ResponseEntity.ok(resourceList);
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/allReadPlaca")
	public ResponseEntity<List<Placa>> requestMethodName() {

		//List<Placa> resourceList = placaRepository.findAll();
		List<Placa> resourceList = placaService.findAll();

		if (resourceList != null) {
			return ResponseEntity.ok(resourceList);
		}
		return ResponseEntity.notFound().build();
	}

}
