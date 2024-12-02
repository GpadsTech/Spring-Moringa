package com.gpads.moringa.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpads.moringa.entities.Placa;
import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.service.PlacaOutPutService;
import com.gpads.moringa.service.PlacaService;
import com.gpads.moringa.statistics.AnaliseService;
import com.gpads.moringa.statistics.IntervaloTemporalEstatistico;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MainController {
    
    private final PlacaOutPutService placaOutPutService;
    
    private final PlacaService placaService;
    
    private final AnaliseService analiseService;
    
    public MainController(PlacaOutPutService placaOutPutService, PlacaService placaService,
            AnaliseService analiseService) {
        this.placaOutPutService = placaOutPutService;
        this.placaService = placaService;
        this.analiseService = analiseService;
    }

   

    @GetMapping("/findLatestDataByPlacaId/{idPlaca}")
    public ResponseEntity<PlacaOutPut> lastData(@PathVariable("idPlaca") ObjectId idPlaca) {

        PlacaOutPut resource = placaOutPutService.findLatestByPlacaId(idPlaca);

        if (resource != null) {
            return ResponseEntity.ok(resource);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByPlacaIdAndDateRange/{idPlaca}/{startDate}/{endDate}")
    public ResponseEntity<List<PlacaOutPut>> findByPlacaIdAndDateRange(@PathVariable("idPlaca") ObjectId idPlaca,
            @PathVariable("startDate") String startDate,
            @PathVariable("endDate") String endDate) {

        Date starD = Date.from(Instant.parse(startDate));
        Date endD = Date.from(Instant.parse(endDate));

        List<PlacaOutPut> resourceList = placaOutPutService.findByPlacaIdAndDateRange(idPlaca, starD, endD);

        if (resourceList != null) {
            return ResponseEntity.ok(resourceList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/processDataPlacaAndDateRange/{idPlaca}/{startDate}/{endDate}")
    public ResponseEntity<List<IntervaloTemporalEstatistico>> processDataPlacaAndDateRange(@PathVariable("idPlaca") ObjectId idPlaca,
            @PathVariable("startDate") String startDate,
            @PathVariable("endDate") String endDate) {
        Date starD = Date.from(Instant.parse(startDate));
        Date endD = Date.from(Instant.parse(endDate));

        List<PlacaOutPut> resourceList = placaOutPutService.findByPlacaIdAndDateRange(idPlaca, starD, endD);
        List<IntervaloTemporalEstatistico> resourceListAnalisada = analiseService.analise(resourceList);

        if (resourceListAnalisada != null) {
            return ResponseEntity.ok(resourceListAnalisada);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/allReadPlaca")
    public ResponseEntity<List<Placa>> allReadPlaca() {
        List<Placa> resourceList = placaService.findAll();

        if (resourceList != null) {
            return ResponseEntity.ok(resourceList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastroPlaca/{modelo}/{lat}/{lon}")
    public ResponseEntity<List<Placa>> cadastroEbuscarTodasAsPlacas(@PathVariable("modelo") String modelo,
            @PathVariable("lat") String lat,
            @PathVariable("lon") String lon) {
        Float la = Float.valueOf(lat);
        Float lo = Float.valueOf(lon);
        Placa p = new Placa(modelo, la, lo);
        placaService.save(p);
        List<Placa> resourList = placaService.findAll();
        if (resourList != null) {
            return ResponseEntity.ok(resourList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/placaAll")
    public ResponseEntity<List<Placa>> placaReadAll() {
        List<Placa> resourList = placaService.findAll();
        if (resourList != null) {
            return ResponseEntity.ok(resourList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/placaOutPutAll")
    public ResponseEntity<List<PlacaOutPut>> placaOutPutReadAll() {

        List<PlacaOutPut> resourList = placaOutPutService.findAll();

        if (resourList != null) {
            return ResponseEntity.ok(resourList);
        }
        return ResponseEntity.notFound().build();
    }

}
