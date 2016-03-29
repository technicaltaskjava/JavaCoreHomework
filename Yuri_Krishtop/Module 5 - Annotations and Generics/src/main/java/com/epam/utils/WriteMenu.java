package com.epam.utils;

import com.epam.task2.Unit;

import java.util.Scanner;

/**
 * Created by Yuriy Krishtop on 27.03.2016.
 */
public class WriteMenu {

    public static Unit adElement() {
        Scanner inputFirst = new Scanner(System.in);
        Unit test;
        if (inputFirst.hasNextBoolean()) {
            Boolean var = inputFirst.nextBoolean();
            test = Unit.create(var);
        } else if (inputFirst.hasNextInt()) {
            int var = inputFirst.nextInt();
            test = Unit.create(var);
        } else if (inputFirst.hasNextDouble()) {
            double var = inputFirst.nextDouble();
            test = Unit.create(var);
        } else {
            String var = inputFirst.nextLine();
            test = Unit.create(var);
        }
        return test;
    }
}
