package com.epam.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Olga Kramska on 28-May-16.
 */
public class PredictionDTO implements Serializable{
    private int id;
    private String cookie;
    private String prediction;
    private Date timeAdded;

    public PredictionDTO(int cookieId, String cookie, String prediction, Date timeAdded) {
        this.id = cookieId;
        this.cookie = cookie;
        this.prediction = prediction;
        this.timeAdded = timeAdded;
    }

    public int getId() {
        return id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }
}
