package entities;

import dao.IdAble;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public class Cookie implements IdAble {

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