package javase.t02.concreteapp;

import javase.t02.abstractapp.App;

/**
 * Our concrete application for Android
 * Created by Yury Vislobodsky on 24.04.2016.
 */
public class AndroidApp implements App {
    private String fileName = "noname";

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMessage() {
        return "File " + fileName + ".apk is placed in the Google Play Market";
    }
}
