package com.gpads.moringa.statisticsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gpads.moringa.entities.PlacaOutPut;
import com.gpads.moringa.statistics.AnaliseEstatistica;
import com.gpads.moringa.statistics.AnaliseService;
import com.gpads.moringa.statistics.IntervaloTemporalEstatistico;

public class AnaliseServiceTest {
    private static AnaliseService analiseService;
    private static final List<Float> lista = new ArrayList<>();

    @BeforeAll
    public static void beforeTest(){
        analiseService = new AnaliseService();
        
        lista.addAll(List.of(1.1f, 1.2f, 1.2f, 1.3f, 1.4f, 1.5f, 1.6f, 1.7f, 1.8f));

    }
    @Test
    void testMedia(){
        float result = analiseService.media(lista);
        assertEquals(1.42f, result, 0.01f);
    }

    @Test
    void testMediana(){
        List<Float> results = analiseService.mediana(lista);
        for(float r : results){
            assertEquals(1.4f, r);
        }
    }

    @Test
    void testModa(){
        List<Float> results = analiseService.moda(lista);
        for(float r : results){
            assertEquals(1.2f, r);
        }
    }

    @Test
    void testQ1(){
        float result = analiseService.q1(lista);
        assertEquals(1.2f, result);
    }

    @Test
    void testQ3(){
        float result = analiseService.q3(lista);
        assertEquals(1.65f, result, 0.01f);
    }

    @Test
    void testExtrairValores(){
        List<PlacaOutPut> dataOfPlaca = new ArrayList<>();
        dataOfPlaca.addAll(List.of(new PlacaOutPut(25f), new PlacaOutPut(30f)));
        List<Float> result = analiseService.extrairValores(dataOfPlaca, "temperatura");
        assertEquals(25f, result.get(0), 0.0001f);
        assertEquals(30f, result.get(1), 0.0001f);
    }

    @Test
    void testAnalise(){
        PlacaOutPut p1 = new PlacaOutPut(new Date(new GregorianCalendar(2024, 1, 1, 12, 0).getTimeInMillis()), 20f, 50f);
        PlacaOutPut p2 = new PlacaOutPut(new Date(new GregorianCalendar(2024, 1, 1, 13, 30).getTimeInMillis()), 30f, 100f);
        List<PlacaOutPut> dados = List.of(p1, p2);
        List<IntervaloTemporalEstatistico> dadosAnalisados = analiseService.analise(dados);

        IntervaloTemporalEstatistico dadoAnalisado1 = dadosAnalisados.get(0);
        Map<String, AnaliseEstatistica> mapDadoAnalisado1 = dadoAnalisado1.getMapaDados();
        AnaliseEstatistica temperaturaDoDadoAnalisado1 = mapDadoAnalisado1.get("temperatura");
        assertEquals(20f, temperaturaDoDadoAnalisado1.getMedia(), 0.01f);

        IntervaloTemporalEstatistico dadoAnalisado2 = dadosAnalisados.get(1);
        Map<String, AnaliseEstatistica> mapDadoAnalisado2 = dadoAnalisado2.getMapaDados();
        AnaliseEstatistica umidadeDoDadoAnalisado2 = mapDadoAnalisado2.get("umidade");
        assertEquals(100f, umidadeDoDadoAnalisado2.getMedia(), 0.01f);
    }
}
