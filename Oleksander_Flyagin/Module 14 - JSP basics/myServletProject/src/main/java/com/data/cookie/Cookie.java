package com.data.cookie;


public class Cookie {

    private int id;
    private String prediction;



    public Cookie(int id, String prediction) {
        this.id = id;
        this.prediction = prediction;

    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return "[id " + getId()  + " " + "Prediction " + getPrediction()+ "]";
    }
}
