package com.epam;

import com.epam.task1.CreateTable;
import com.epam.task1.TableTransformJdbc;
import com.epam.task1.TableTransformSql;
import com.epam.task2.DemoDao;

import java.util.Scanner;

/**
 * Created by Yuriy Krishtop on 22.04.2016.
 */
public class Main {

    public static final String FORMAT_STRING_PIPE;
    public static final String FORMAT_STRING_PIPE_LINE;
    public static final String FORMAT_LINE_STRING_PIPE_LINE;
    public static final String LINE;

    static {
        FORMAT_STRING_PIPE = "%-85s %5s%n";
        FORMAT_STRING_PIPE_LINE = "%-70s %20s%n%s%n";
        FORMAT_LINE_STRING_PIPE_LINE = "%s%n%-70s %20s%n%s%n";
        LINE = "+-----------------------------------------------------------------------------------------+";
    }

    Main() {
    }

    public static void main(String[] args) {
        System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "|  Home Work 11: JDBC", "|", LINE);
        System.out.printf(FORMAT_STRING_PIPE, "|  Please enter an integer number for start:", "|");
        System.out.printf(FORMAT_STRING_PIPE, "|    1 - Run table transformation using JDBC", "|");
        System.out.printf(FORMAT_STRING_PIPE, "|    2 - Run table transformation using SQL-commands", "|");
        System.out.printf(FORMAT_STRING_PIPE, "|    3 - Run DAO", "|");
        System.out.printf(FORMAT_STRING_PIPE_LINE, "|    4 - close", "|", LINE);
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int idOperation = in.nextInt();
            switch (idOperation) {
                case 1:
                    CreateTable.createTableUsers();
                    TableTransformJdbc.main();
                    break;
                case 2:
                    CreateTable.createTableUsers();
                    TableTransformSql.main();
                    break;
                case 3:
                    CreateTable.createTableUsers();
                    CreateTable.createTableCookies();
                    CreateTable.createTableMetadata();
                    DemoDao.main();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "| Command not found, please try again.",
                            "|", LINE);
            }
        } else {
            System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "| Error, enter an integer.", "|", LINE);
        }
    }

}