package task2;

import java.io.*;


/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public class Main {
    public static void main(String[] args)  {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.info("Info");
        crazyLogger.conf("Config");
        crazyLogger.warn("Warning");
        crazyLogger.warn("Warning - warning");
        crazyLogger.logsByLevel(Level.WARNING);
        try {
            crazyLogger.logsByDate(56, 99, 5656);
        } catch (WrongDateException e) {
            System.out.println("Wrong date");
        }


    }
}
