package com.gpads.moringa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.repositories.PlacaOutPutRepository;
import com.gpads.moringa.repositories.PlacaRepository;

import jakarta.annotation.PostConstruct;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@CrossOrigin(
    origins = "https://interface-morhinga.vercel.app", 
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
    allowedHeaders = "*"
)
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	PlacaRepository placaRepository;

	@Autowired
	PlacaOutPutRepository placaOutPutRepository;
	

	@RequestMapping("/findLatestDataByPlacaId/{idPlaca}")
	public ResponseEntity<PlacaOutPut> lastData(@PathVariable("idPlaca") Long idPlaca) {

		List<PlacaOutPut> resource = placaOutPutRepository.findLatestByPlacaId(idPlaca);

		if (resource != null)
			return ResponseEntity.ok(resource.get(0));

		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/findByPlacaIdAndDateRange/{idPlaca}/{startDate}/{endDate}")
	public ResponseEntity<List<PlacaOutPut>> findByPlacaIdAndDateRange(@PathVariable("idPlaca") Long idPlaca,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {

		Date starD = Date.from(Instant.parse(startDate));
		Date endD = Date.from(Instant.parse(endDate));

		List<PlacaOutPut> resourceList = placaOutPutRepository.findByPlacaIdAndDateRange(idPlaca, starD, endD);

		if (resourceList != null) {
			return ResponseEntity.ok(resourceList);
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping("/allReadPlaca")
	public ResponseEntity<List<Placa>> requestMethodName() {

		List<Placa> resourceList = placaRepository.findAll();

		if (resourceList != null) {
			return ResponseEntity.ok(resourceList);
		}
		return ResponseEntity.notFound().build();
	}

	@PostConstruct
	public  void inicializadorDados(){
        // placas
		Placa placa1 = new Placa("Modelo A", -8.0476f, -34.8770f); 
        placaRepository.save(placa1);
		Placa placa2 = new Placa("Modelo B", -8.2764f, -35.9657f); 
		placaRepository.save(placa2);
		Placa placa3 = new Placa("Modelo C", -8.2927f, -36.8814f); 
		placaRepository.save(placa3);
		Placa placa4 = new Placa("Modelo D", -8.5570f, -35.7810f); 
		placaRepository.save(placa4);
		Placa placa5 = new Placa("Modelo E", -8.0869f, -35.3285f); 
		placaRepository.save(placa5);

		//dados
		PlacaOutPut data1_placa1 = new PlacaOutPut(new Date(1695133200000L), 25.0f, 60.0f, 1013.0f, 300.0f, 400.0f,
				80.0f, 5.0f, 12.0f, 1500.0f, placa1);
		PlacaOutPut data2_placa1 = new PlacaOutPut(new Date(1695133500000L), 26.0f, 62.0f, 1012.0f, 320.0f, 420.0f,
				82.0f, 5.5f, 12.5f, 1550.0f, placa1);
		PlacaOutPut data3_placa1 = new PlacaOutPut(new Date(1695133800000L), 24.5f, 59.0f, 1014.0f, 310.0f, 410.0f,
				81.0f, 6.0f, 11.0f, 1480.0f, placa1);
		PlacaOutPut data4_placa1 = new PlacaOutPut(new Date(1695134100000L), 27.0f, 63.0f, 1011.0f, 330.0f, 430.0f,
				85.0f, 5.8f, 13.0f, 1520.0f, placa1);
		PlacaOutPut data5_placa1 = new PlacaOutPut(new Date(1695134400000L), 28.0f, 65.0f, 1010.0f, 340.0f, 440.0f,
				87.0f, 6.2f, 12.0f, 1570.0f, placa1);

		PlacaOutPut data1_placa2 = new PlacaOutPut(new Date(1695133200000L), 24.0f, 55.0f, 1012.5f, 310.0f, 395.0f,
				78.0f, 4.5f, 11.0f, 1450.0f, placa2);
		PlacaOutPut data2_placa2 = new PlacaOutPut(new Date(1695133500000L), 25.5f, 57.0f, 1011.0f, 315.0f, 400.0f,
				79.0f, 4.8f, 11.5f, 1470.0f, placa2);
		PlacaOutPut data3_placa2 = new PlacaOutPut(new Date(1695133800000L), 23.5f, 54.0f, 1013.0f, 325.0f, 405.0f,
				80.0f, 5.2f, 10.5f, 1440.0f, placa2);
		PlacaOutPut data4_placa2 = new PlacaOutPut(new Date(1695134100000L), 26.0f, 58.0f, 1010.0f, 320.0f, 410.0f,
				81.0f, 5.0f, 12.0f, 1460.0f, placa2);
		PlacaOutPut data5_placa2 = new PlacaOutPut(new Date(1695134400000L), 27.5f, 60.0f, 1009.0f, 330.0f, 415.0f,
				83.0f, 5.5f, 11.0f, 1490.0f, placa2);

		PlacaOutPut data1_placa3 = new PlacaOutPut(new Date(1695133200000L), 22.0f, 50.0f, 1011.5f, 300.0f, 390.0f,
				75.0f, 4.0f, 10.0f, 1400.0f, placa3);
		PlacaOutPut data2_placa3 = new PlacaOutPut(new Date(1695133500000L), 23.5f, 52.0f, 1010.0f, 305.0f, 395.0f,
				76.0f, 4.3f, 10.5f, 1410.0f, placa3);
		PlacaOutPut data3_placa3 = new PlacaOutPut(new Date(1695133800000L), 21.0f, 49.0f, 1012.0f, 295.0f, 380.0f,
				74.0f, 4.5f, 9.5f, 1380.0f, placa3);
		PlacaOutPut data4_placa3 = new PlacaOutPut(new Date(1695134100000L), 24.0f, 53.0f, 1009.0f, 310.0f, 400.0f,
				78.0f, 4.8f, 11.0f, 1420.0f, placa3);
		PlacaOutPut data5_placa3 = new PlacaOutPut(new Date(1695134400000L), 25.0f, 54.0f, 1008.0f, 315.0f, 405.0f,
				79.0f, 5.0f, 10.0f, 1430.0f, placa3);

		PlacaOutPut data1_placa4 = new PlacaOutPut(new Date(1695133200000L), 20.0f, 45.0f, 1013.5f, 290.0f, 375.0f,
				70.0f, 3.5f, 9.0f, 1350.0f, placa4);
		PlacaOutPut data2_placa4 = new PlacaOutPut(new Date(1695133500000L), 21.5f, 46.0f, 1012.0f, 295.0f, 380.0f,
				71.0f, 3.8f, 9.5f, 1360.0f, placa4);
		PlacaOutPut data3_placa4 = new PlacaOutPut(new Date(1695133800000L), 19.0f, 44.0f, 1014.0f, 285.0f, 370.0f,
				69.0f, 4.0f, 8.5f, 1340.0f, placa4);
		PlacaOutPut data4_placa4 = new PlacaOutPut(new Date(1695134100000L), 22.0f, 47.0f, 1011.0f, 300.0f, 385.0f,
				73.0f, 4.2f, 10.0f, 1370.0f, placa4);
		PlacaOutPut data5_placa4 = new PlacaOutPut(new Date(1695134400000L), 23.0f, 48.0f, 1010.0f, 305.0f, 390.0f,
				74.0f, 4.5f, 9.0f, 1380.0f, placa4);

		PlacaOutPut data1_placa5 = new PlacaOutPut(new Date(1695133200000L), 18.0f, 40.0f, 1010.5f, 280.0f, 360.0f,
				68.0f, 3.0f, 8.0f, 1300.0f, placa5);
		PlacaOutPut data2_placa5 = new PlacaOutPut(new Date(1695133500000L), 19.5f, 41.0f, 1009.0f, 285.0f, 365.0f,
				69.0f, 3.2f, 8.5f, 1310.0f, placa5);
		PlacaOutPut data3_placa5 = new PlacaOutPut(new Date(1695133800000L), 17.0f, 39.0f, 1011.0f, 275.0f, 355.0f,
				67.0f, 3.5f, 7.5f, 1280.0f, placa5);
		placaOutPutRepository.save(data1_placa1);
		placaOutPutRepository.save(data2_placa1);
		placaOutPutRepository.save(data3_placa1);
		placaOutPutRepository.save(data4_placa1);
		placaOutPutRepository.save(data5_placa1);

		placaOutPutRepository.save(data1_placa2);
		placaOutPutRepository.save(data2_placa2);
		placaOutPutRepository.save(data3_placa2);
		placaOutPutRepository.save(data4_placa2);
		placaOutPutRepository.save(data5_placa2);

		placaOutPutRepository.save(data1_placa3);
		placaOutPutRepository.save(data2_placa3);
		placaOutPutRepository.save(data3_placa3);
		placaOutPutRepository.save(data4_placa3);
		placaOutPutRepository.save(data5_placa3);

		placaOutPutRepository.save(data1_placa4);
		placaOutPutRepository.save(data2_placa4);
		placaOutPutRepository.save(data3_placa4);
		placaOutPutRepository.save(data4_placa4);
		placaOutPutRepository.save(data5_placa4);

		placaOutPutRepository.save(data1_placa5);
		placaOutPutRepository.save(data2_placa5);
		placaOutPutRepository.save(data3_placa5);
    }
}
