package com.kokhanyuk.patterns.singleton;


import java.util.ArrayList;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToRunSingleton {

    private ToRunSingleton() {
    }

    public static void main(String[] args) {
        MyLogger log = MyLogger.getLogger();

        ArrayList<MyAccount> accounts = new ArrayList();
        for (int i = 0; i < 10; i++) {
            accounts.add(new MyAccount(i + 1));
        }
        for (MyAccount account : accounts) {
            account.setBalance(100);
        }
        log.printCommadLine();
    }
}

