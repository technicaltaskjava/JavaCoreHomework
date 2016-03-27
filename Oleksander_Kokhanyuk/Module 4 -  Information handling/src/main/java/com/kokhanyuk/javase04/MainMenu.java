package com.kokhanyuk.javase04;

import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase04.crazylogger.CrazyLogger;
import com.kokhanyuk.javase04.crazylogger.CrazyLoggerMenu;
import com.kokhanyuk.javase04.rocessing.SentenceSearch;
import com.kokhanyuk.javase04.rocessing.WordsSort;

import java.util.Scanner;

import static java.io.File.separator;


/**
 * MainMenu
 * <p/>
 * This class starts parts of my homework
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MainMenu {
    public void mainMenu() {
        String st;
        String fileSort = "Data" + separator + "4  Information Handling Homework.txt";
        String fileSearch = "Data" + separator + "4. Information handling_task_attachment.html";
        String fileMenu = "Data" + separator + "MainMenu.txt";
        int it = 0;
        CrazyLogger log = new CrazyLogger("MainMenu");
        WordsSort sort = new WordsSort();
        SentenceSearch search = new SentenceSearch();
        FileIO fio = new FileIO();
        Scanner in = new Scanner(System.in);
        System.out.println(fio.readFile(fileMenu, "UTF-8"));
        while (it != 8) {
            System.out.println("Main menu.\nPress enter item number(Menu -7; Exit-8):");
            st = in.nextLine();
            try {
                it = Integer.parseInt(st);
            } catch (NumberFormatException e) {
                log.err("Error input data in mainMenu", e.toString());
            }
            switch (it) {
                case 1:
                    System.out.println(sort.changeWordPlases(fileSort));
                    System.out.println("");
                    break;
                case 2:
                    System.out.println(sort.sortVowels(fileSort));
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Enter the word length");
                    st = in.nextLine();
                    try {
                        int length = Integer.parseInt(st);
                        System.out.println(sort.dellWordLength(fileSort, length));
                    } catch (NumberFormatException e) {
                        log.err("Error input data in mainMenu", e.toString());
                    }
                    System.out.println("");
                    break;
                case 4:
                    System.out.println(sort.wordConverter(fileSort));
                    System.out.println("");
                    break;
                case 5:
                    CrazyLoggerMenu crazyLog = new CrazyLoggerMenu();
                    crazyLog.loggerMenu();
                    System.out.println("");
                    break;
                case 6:
                    search.textSearcher(fileSearch);
                    System.out.println("");
                    break;
                case 7:
                    System.out.println(fio.readFile(fileMenu, "UTF-8"));
                    break;
                case 8:
                    System.out.println("");
                    break;
                default:
                    System.out.println("\n" + "Item not found");
                    log.info("Item not found");
                    break;
            }
        }
        in.close();
    }
}