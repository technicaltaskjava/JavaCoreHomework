package com.kokhanyuk.javase04;

import com.kokhanyuk.javase04.crazylogger.CrazyLogger;

/**
 * Main
 * The main class of the program
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        CrazyLogger log = new CrazyLogger("main");
        MainMenu menu= new MainMenu();
        menu.mainMenu();
        log.save("CrazyLogger.log");
    }
}
