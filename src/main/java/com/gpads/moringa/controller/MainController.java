package com.gpads.moringa.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.service.PlacaOutPutService;
import com.gpads.moringa.service.PlacaService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MainController {

	

	@Autowired
	private PlacaOutPutService placaOutPutService;

	@Autowired
	private PlacaService placaService;
	

	@RequestMapping("/findLatestDataByPlacaId/{idPlaca}")
	public ResponseEntity<PlacaOutPut> lastData(@PathVariable("idPlaca") ObjectId idPlaca) {

		//List<PlacaOutPut> resource = placaOutPutRepository.findLatestByPlacaId(idPlaca);
		PlacaOutPut resource = placaOutPutService.findLatestByPlacaId(idPlaca);
		

		if (resource != null)
			return ResponseEntity.ok(resource);

		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/findByPlacaIdAndDateRange/{idPlaca}/{startDate}/{endDate}")
	public ResponseEntity<List<PlacaOutPut>> findByPlacaIdAndDateRange(@PathVariable("idPlaca") ObjectId idPlaca,
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
	public ResponseEntity<List<Placa>> allReadPlaca() {

		//List<Placa> resourceList = placaRepository.findAll();
		List<Placa> resourceList = placaService.findAll();

		if (resourceList != null) {
			return ResponseEntity.ok(resourceList);
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/cadastroPlaca/{modelo}/{lat}/{lon}")
	public ResponseEntity<List<Placa>> cadastroEbuscarTodasAsPlacas(@PathVariable("modelo") String modelo,
																	@PathVariable("lat") String lat,
																	@PathVariable("lon") String lon){
		Float la = Float.parseFloat(lat);
		Float lo =Float.parseFloat(lon);
		Placa p = new Placa(modelo, la, lo);
		placaService.save(p);
		List<Placa> resourList = placaService.findAll();
		if(resourList!= null){
			return ResponseEntity.ok(resourList);
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/placaAll")
	public ResponseEntity<List<Placa>> placaReadAll(){
		List<Placa> resourList = placaService.findAll();
		if(resourList != null){
			return ResponseEntity.ok(resourList);
		}
		return ResponseEntity.notFound().build();
	}
	@RequestMapping("/placaOutPutAll")
	public ResponseEntity<List<PlacaOutPut>> placaOutPutReadAll() {
    

		List<PlacaOutPut> resourList = placaOutPutService.findAll();

		if(resourList != null){
			return ResponseEntity.ok(resourList);
		}
		return ResponseEntity.notFound().build();
	}
	

}
