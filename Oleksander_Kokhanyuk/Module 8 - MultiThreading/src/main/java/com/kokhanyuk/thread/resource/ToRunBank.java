package com.kokhanyuk.thread.resource;


/**
 * ToRunBank
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToRunBank {

    private ToRunBank() {
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.operations();
    }
}
