package com.epam.model;

/**
 * Created by Olga Kramska on 08-May-16.
 */
public class Cookie {
    private int id;
    private String name;
    private String prediction;

    public Cookie(int id, String name, String prediction) {
        this.id = id;
        this.name = name;
        this.prediction = prediction;
    }

    public Cookie(String name, String prediction) {
        this.name = name;
        this.prediction = prediction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
