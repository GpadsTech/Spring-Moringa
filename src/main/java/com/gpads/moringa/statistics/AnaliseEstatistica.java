package com.gpads.moringa.statistics;

import java.util.List;

public class AnaliseEstatistica {
    private float media;
    private List<Float> moda;
    private List<Float> mediana;
    private float q1;
    private float q2;


    public AnaliseEstatistica() {
    }

    public AnaliseEstatistica(float media, List<Float> moda, List<Float> mediana, float q1, float q2) {
        this.media = media;
        this.moda = moda;
        this.mediana = mediana;
        this.q1 = q1;
        this.q2 = q2;
    }


    public float getMedia() {
        return this.media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public List<Float> getModa() {
        return this.moda;
    }

    public void setModa(List<Float> moda) {
        this.moda = moda;
    }

    public List<Float> getMediana() {
        return this.mediana;
    }

    public void setMediana(List<Float> mediana) {
        this.mediana = mediana;
    }

    public float getQ1() {
        return this.q1;
    }

    public void setQ1(float q1) {
        this.q1 = q1;
    }

    public float getQ2() {
        return this.q2;
    }

    public void setQ2(float q2) {
        this.q2 = q2;
    }
    

}