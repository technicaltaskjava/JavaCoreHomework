package com.epam;

import com.epam.dao.DaoRun;
import com.epam.jdbc.JdbcRun;

import java.util.Scanner;
import java.util.logging.Logger;

public class JdbcHandlingRun {
    static Scanner userInput;
    private static Logger log = Logger.getLogger(JdbcHandlingRun.class.getName());
    private static final String DB_CREATE = "src/main/resources/create.sql";
    private static final String DB_DROP = "src/main/resources/drop.sql";

    private JdbcHandlingRun() {
    }

    public static void main(String[] args) {
        userInput = new Scanner(System.in);

        boolean makeTask = true;
        while (makeTask) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] Database Handling. Create Tables (Homework 10).");
            System.out.println("[2] Database Handling. Drop Tables.");
            System.out.println("[3] Database Handling. JDBC using.");
            System.out.println("[4] Database Handling. DAO with Connection Pool using.");
            System.out.println("[5] Exit program.");
            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1:
                        CreateCookies.operateDatabase(DB_CREATE);
                        System.out.println("Tables created.\n");
                        break;
                    case 2:
                        CreateCookies.operateDatabase(DB_DROP);
                        System.out.println("Tables droped.\n");
                        break;
                    case 3:
                        JdbcRun.jdbcRun();
                        break;
                    case 4:
                        DaoRun.daoRun();
                        break;
                    case 5:
                        makeTask = false;
                        break;
                    default:
                        System.out.println("Incorrect number. You must input one digit from 1 to 5.\n");
                        break;
                }
            }
            catch (NumberFormatException userException) {
                log.info(String.valueOf(userException));
                System.out.println("Incorrect input.\n");
            }
        }
    }
}
