package com.epam.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Oleg on 20.04.2016.
 */
public class AccountFileReader {
    private AccountFileReader() {
    }

    public static List<String> getLines(String fileName) throws FileNotFoundException {

        List<String> accountsOperations = new LinkedList<>();
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNext()) {
            accountsOperations.add(sc.nextLine());
        }
        sc.close();
        return accountsOperations;
    }


}
