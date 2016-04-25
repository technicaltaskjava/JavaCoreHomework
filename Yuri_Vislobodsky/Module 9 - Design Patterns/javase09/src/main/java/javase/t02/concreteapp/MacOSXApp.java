package javase.t02.concreteapp;

import javase.t02.abstractapp.App;

/**
 * Our concrete application for Mac OSX
 * Created by Yury Vislobodsky on 24.04.2016.
 */
public class MacOSXApp implements App {
    private String fileName = "noname";

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMessage() {
        return "File " + fileName + ".ipa is placed in the App Store";
    }
}
