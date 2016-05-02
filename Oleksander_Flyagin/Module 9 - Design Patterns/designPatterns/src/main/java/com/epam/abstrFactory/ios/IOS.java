package com.epam.abstrfactory.ios;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IOS {
    private  String type;


    public void setType(String type) {
        this.type = type;
    }


    public void getTime() {
        System.out.println(type);
        System.out.println("TIME : " +  new SimpleDateFormat("HH:mm:ss").format(new Date()));

    }


    public void getCalendar() {
        System.out.println(type);
        System.out.println( "DATE : " + new SimpleDateFormat("yyyy.mm.dd").format(new Date()));

    }


    public void getMessege() {
        System.out.println(type);
        System.out.println("Hello apple's friend");


    }
    public void getMaps() {
        System.out.println(type);
        System.out.println("Siri helps you ");

    }


    public void getInternat() {
        System.out.println(type);
        System.out.println("Open  Safari");
    }

    public void getSynhronise()
    {
        System.out.println(type);
        System.out.println("connect");

    }


}
