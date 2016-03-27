package com.kokhanyuk.javase04.crazylogger;

import com.kokhanyuk.javase03.actions.FileIO;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static java.io.File.separator;

/**
 * CrazyLoggerMenu
 *This class provides access to CrazyLogger store, allows you
 * to search messages and output to the output stream
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CrazyLoggerMenu {

    public void loggerMenu() {
        CrazyLogger log = new CrazyLogger("loggerMenu");
        FileIO fio = new FileIO();
        String fileLoggerMenu = "Data" + separator + "CrazyLoggerMenu.txt";
        int it = 0;
        String st = "";
        Scanner in = new Scanner(System.in);
        System.out.println(fio.readFile(fileLoggerMenu, "UTF-8"));
        while (it != 5) {
            System.out.println("CrazyLogger menu(Exit-5):");
            st = in.nextLine();
            try {
                it = Integer.parseInt(st);
            } catch (NumberFormatException e) {
                log.err("Error input data in CrazyLogger menu", e.toString());
            }
            switch (it) {
                case 1:
                    notesSearcher("ERROR", false);
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Enter key word");
                    st = in.nextLine();
                    notesSearcher(st, false);
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Enter key word");
                    st = in.nextLine();
                    notesSearcher(st, true);
                    System.out.println("");
                    break;
                case 4:
                    log.printCommadLine();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\n" + "Item not found");
                    log.info("Item not found");
                    break;
            }
        }
    }

    public void notesSearcher(String key, boolean writeStream) {
        CrazyLogger log = new CrazyLogger("notesSearcher");
        String fileStream = "Data" + separator + "LoggerSearchResult.txt";
        String pattern = "";
        pattern = key;
        Pattern pl = Pattern.compile(pattern);
        Matcher ml = pl.matcher(CrazyLogger.log);
        int start = 0;
        int middle = 0;
        int end = 0;
        String text = "";
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileStream)));
            while (ml.find()) {
                middle = ml.end();
                end = CrazyLogger.log.indexOf(";", middle);
                start = startSearcher(end);
                text = CrazyLogger.log.substring(start + 2, end);
                if (writeStream) {
                    out.writeUTF(text);
                } else {
                    System.out.println(text.trim());
                }
            }
            out.close();
        } catch (PatternSyntaxException e) {
            log.err("Error in notesSearcher", e.toString());
        } catch (IOException e) {
            log.err("Error write to Output Stream", e.toString());
        }

        ml.reset();
    }

    private int startSearcher(int end) {
        int start = 0;
        int startCheck = 0;
        int startLast = 0;
        int startChecLast = 0;
        for (int i = 0; i < CrazyLogger.log.length(); i++) {
            startCheck = CrazyLogger.log.lastIndexOf(";", startLast);
            if (startCheck == end) {
                if (start == 0) {
                    start = -2;
                }
                break;
            }
            if (startCheck != startChecLast) {
                start = startLast;
            }
            startChecLast = startCheck;
            startLast++;
        }
        return start;
    }
}
