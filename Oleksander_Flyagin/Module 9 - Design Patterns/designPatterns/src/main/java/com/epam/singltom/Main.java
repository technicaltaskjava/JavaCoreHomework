package com.epam.singltom;

public class Main {
    private Main() {
    }

    public static void main(String[] args)  {

        TreadCheck tread1 = new TreadCheck();
        tread1.setBegin(15);
        tread1.setLast(40);

        TreadCheck tread2 = new TreadCheck();
        tread2.setBegin(70);
        tread2.setLast(120);

        tread1.start();
        tread2.start();

    }
}
