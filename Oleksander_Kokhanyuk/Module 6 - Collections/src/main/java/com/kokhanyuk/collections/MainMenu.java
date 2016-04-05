package com.kokhanyuk.collections;

import com.kokhanyuk.collections.nombersstorage.RunNumber;
import com.kokhanyuk.collections.parking.RunParking;
import com.kokhanyuk.collections.readfile.FileRead;
import com.kokhanyuk.collections.taxistation.RunTaxiStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * MainMenu
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MainMenu {

    Logger log = LoggerFactory.getLogger(MainMenu.class);
    String headMenu = "\n1-Taxi station and MyList;\n2-Numbers storage;\n3-Fast parking;\n4-Use collections.";
    String exitMenu = "\nEnter menu item (or press 5 to exit).";
    String errorData = "\nError entering data!";
    String collections = "collections.txt";

    public void menu() {
        RunTaxiStation station = new RunTaxiStation();
        RunNumber storage = new RunNumber();
        RunParking fast = new RunParking();
        FileRead fr = new FileRead();
        String st;
        int it = 0;
        Scanner in = new Scanner(System.in);
        log.info(headMenu);
        while (it != 5) {
            log.info(exitMenu);
            st = in.nextLine();
            try {
                it = Integer.parseInt(st);
            } catch (NumberFormatException e) {
                log.warn(errorData);
            }
            switch (it) {
                case 1:
                    station.taxi();
                    break;
                case 2:
                    storage.number();
                    break;
                case 3:
                    fast.parking();
                    break;
                case 4:
                    log.info(fr.readFile(collections));
                    break;
                case 5:
                    break;
                default:
                    log.info(errorData);
                    break;
            }
        }
        in.close();
    }
}