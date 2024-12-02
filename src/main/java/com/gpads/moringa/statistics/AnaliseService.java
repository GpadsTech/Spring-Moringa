package com.gpads.moringa.statistics;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.PlacaOutPut;

@Service
public class AnaliseService {

    public List<IntervaloTemporalEstatistico> analise(List<PlacaOutPut> dados) {
        // Agrupar dados por intervalo de uma hora
        Map<String, List<PlacaOutPut>> dadosAgrupados = dados.stream()
                .collect(Collectors.groupingBy(p -> formatarDataHora(p.getDataHora())));

        List<IntervaloTemporalEstatistico> resultado = new ArrayList<>();

        for (Map.Entry<String, List<PlacaOutPut>> entry : dadosAgrupados.entrySet()) {
            String intervalo = entry.getKey();
            List<PlacaOutPut> dadosIntervalo = entry.getValue();

            Map<String, AnaliseEstatistica> mapaDados = new HashMap<>();

            // Campos numéricos para análise
            String[] campos = {"temperatura", "umidade", "pressao", "luminosidade", "cO2", "qualidadeDoAr", "velocidadeDoVento", "voltagem", "rpm"};

            for (String campo : campos) {
                List<Float> valores = extrairValores(dadosIntervalo, campo);
                AnaliseEstatistica analise = new AnaliseEstatistica();
                analise.setMedia(media(valores));
                analise.setModa(moda(valores));
                analise.setMediana(mediana(valores));
                analise.setQ1(q1(valores));
                analise.setq3(q3(valores));

                mapaDados.put(campo, analise);
            }

            IntervaloTemporalEstatistico intervaloEstatistico = new IntervaloTemporalEstatistico();
            intervaloEstatistico.setIntervaloTempo(intervalo);
            intervaloEstatistico.setMapaDados(mapaDados);

            resultado.add(intervaloEstatistico);
        }

        return resultado;
    }

    private String formatarDataHora(Date dataHora) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00");
        return sdf.format(dataHora);
    }

    @SuppressWarnings("BoxingBoxedValue")
    public List<Float> extrairValores(List<PlacaOutPut> dados, String campo) {
        try {
            MethodHandle getter = MethodHandles.lookup()
                .findGetter(PlacaOutPut.class, campo, Float.class);
            
            return dados.stream()
                .map(p -> {
                    try {
                        return (Float) getter.invoke(p);
                    } catch (Throwable e) {
                        return 0f;
                    }
                })
                .collect(Collectors.toList());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return Collections.emptyList();
        }
    }

    public float media(List<Float> d) {
        float soma = d.stream().reduce(0.0f, Float::sum);
        return soma / d.size();
    }

    public List<Float> mediana(List<Float> d) {
        Collections.sort(d);
        int total = d.size();
        List<Float> medianas = new ArrayList<>();
        if (total % 2 == 0) {
            medianas.add(d.get(total / 2));
            medianas.add(d.get(total / 2 - 1));
        } else {
            medianas.add(d.get(total / 2));
        }
        return medianas;
    }

    public List<Float> moda(List<Float> d) {

        Map<Float, Integer> frequencia = new HashMap<>();
        int maxFrequencia = 0;
        for (Float ocorrencia : d) {
            int count = frequencia.getOrDefault(ocorrencia, 0) + 1;
            frequencia.put(ocorrencia, count);
            maxFrequencia = Math.max(maxFrequencia, count);
        }
        List<Float> modas = new ArrayList<>();
        for (Map.Entry<Float, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() == maxFrequencia) {
                modas.add(entry.getKey());
            }
        }

        return modas;
    }

    public float q1(List<Float> d) {
        if (d.size() == 1) {
            return d.get(0);
        }
        if (d.size() == 2) {
            return d.get(1);
        }
        Collections.sort(d);
        double q1Aux = 0.25 * (d.size() + 1);
        if (q1Aux % 1 == 0) {
            return d.get((int) q1Aux - 1);
        } else {
            int indexInferior = (int) Math.floor(q1Aux) - 1;
            int indexSuperior = (int) Math.ceil(q1Aux) - 1;
            float valorInferior = d.get(indexInferior);
            float valorSuperior = d.get(indexSuperior);
            return (valorInferior + valorSuperior) / 2;
        }
    }

    public float q3(List<Float> d) {
        if (d.size() == 1) {
            return d.get(0);
        }
        if (d.size() == 2) {
            return d.get(1);
        }
        Collections.sort(d);
        double q3Aux = 0.75 * (d.size() + 1);
        if (q3Aux % 1 == 0) {
            return d.get((int) q3Aux - 1);
        } else {
            int indexInferior = (int) Math.floor(q3Aux) - 1;
            int indexSuperior = (int) Math.ceil(q3Aux) - 1;
            float valorInferior = d.get(indexInferior);
            float valorSuperior = d.get(indexSuperior);
            return (valorInferior + valorSuperior) / 2;
        }
    }

}
