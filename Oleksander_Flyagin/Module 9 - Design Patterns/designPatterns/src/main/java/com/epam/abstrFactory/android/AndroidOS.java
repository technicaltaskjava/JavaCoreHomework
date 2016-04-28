package com.epam.abstrfactory.android;

import java.text.SimpleDateFormat;
import java.util.Date;

public  class AndroidOS {
    private  String type;


    public void setType(String type) {
        this.type = type;
    }


    public void getTime() {
        System.out.println(type);
        System.out.println( "TIME : " +  new SimpleDateFormat("HH:mm:ss").format(new Date()));

    }


    public void getCalendar() {
        System.out.println(type);
        System.out.println( "DATE : " + new SimpleDateFormat("yyyy.mm.dd").format(new Date()));

    }


    public void getMessege() {
        System.out.println(type);
        System.out.println("Hi android's friend");


    }
    public void getMaps() {
        System.out.println(type);
        System.out.println("You are hear");

    }


    public void getInternat() {
        System.out.println(type);
        System.out.println("Open  Chrome");
    }

    public void getSynhronise()
    {
        System.out.println(type);
        System.out.println("connect with ANDROID DEVISE");

    }



}
