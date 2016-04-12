package com.kokhanyuk.xml;

import com.kokhanyuk.xml.parsers.DomParser;
import com.kokhanyuk.xml.parsers.SaxParser;
import com.kokhanyuk.xml.parsers.StaxParser;
import com.kokhanyuk.xml.pomcreator.PomCreator;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * MainMenu
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MainMenu {

    static Logger log = Logger.getLogger(MainMenu.class);
    String headMenu = "\n1-SAX parsing;\n2-DOM parsing;\n3-StAX parsing;\n4-Create pom.xml.";
    String exitMenu = "\nEnter menu item (or press 5 to exit).";
    String errorData = "\nError entering data!";

    public void menu() {
        SaxParser sax = new SaxParser();
        DomParser dom = new DomParser();
        StaxParser stax = new StaxParser();
        PomCreator pom = new PomCreator();
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
                log.warn(errorData, e);
            }
            switch (it) {
                case 1:
                    sax.saxParsing();
                    break;
                case 2:
                    dom.domParsing();
                    break;
                case 3:
                    stax.staxParsing();
                    break;
                case 4:
                    pom.pomCreating();
                    break;
                case 5:
                    break;
                default:
                    log.info("Error input data.");
                    break;
            }
        }
        in.close();
    }
}
