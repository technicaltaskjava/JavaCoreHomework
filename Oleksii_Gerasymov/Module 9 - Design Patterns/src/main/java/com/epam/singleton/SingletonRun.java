package com.epam.singleton;

/* Subject Area is Currency Exchange. Every rate is realized by different implementation of Singleton pattern.  */

public class SingletonRun {
    private SingletonRun() {
    }

    public static void currencyExchangeExample() {
        float exchangeSum = 1000;
        String outFormat = "%s%8.2f%s";
        String outText = "Yours " + exchangeSum + " HRN is : ";
        System.out.printf(outFormat,  outText, exchangeSum / EurSingletonEarlyCreation.getInstance().getRate(), " EUR\n");
        System.out.printf(outFormat, outText,  exchangeSum / UsdSingletonSynchronized.getInstance().getRate(), " USD\n");
        System.out.printf(outFormat, outText,  exchangeSum / RubSingletonDoubleLocking.getInstance().getRate(), " RUB\n");
    }
}