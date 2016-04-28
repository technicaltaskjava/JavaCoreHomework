package com.epam.task1;

/**
 * Created by Yuriy Krishtop on 22.04.2016.
 */
public class DemoSingleton {

    private DemoSingleton() {
    }

    public static void main() {
        int a = 2;
        int b = 0;
        LoggerSingleton loggerSingleton = LoggerSingleton.getLoggerSingleton();
        try {
            double c = (double) a / b;
            loggerSingleton.log(c);
        } catch (ArithmeticException e) {
            loggerSingleton.log(e);
        }
        try {
            int[] arrayInt = {0, 1, 2};
            arrayInt[3] = arrayInt[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            loggerSingleton.log(e);
        }
        System.out.println(loggerSingleton.findLog("4-2016"));
    }
}
