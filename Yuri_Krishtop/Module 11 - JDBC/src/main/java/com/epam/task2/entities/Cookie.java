package com.epam.task2.entities;

import com.epam.task2.Idable;

/**
 * Created by Yuriy Krishtop on 08.05.2016.
 */
public class Cookie implements Idable {

    private int id;
    private String prediction;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "id = " + id + " prediction: " + prediction;
    }
}
