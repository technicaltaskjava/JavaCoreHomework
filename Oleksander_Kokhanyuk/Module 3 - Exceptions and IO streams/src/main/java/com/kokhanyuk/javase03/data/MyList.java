package com.kokhanyuk.javase03.data;

import java.io.Serializable;

/**
 * MyList
 * <p/>
 * This class holds a collection of films
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyList implements Serializable {

    private String[][] listOne;
    private String[][] listOneTmp;
    private int n;
    private static final long serialVersionUID = 1L;

    public MyList(int row) {
        listOne = new String[row][2];

    }

    public void addLine(String film, String actor) {
        if (n == (listOne.length) - 1) {
            listOneTmp = new String[listOne.length][2];
            for (int i = 0; i
                    < listOne.length; i++) {
                listOneTmp[i] = listOne[i];
            }
            listOne = null;
            listOne = new String[(listOneTmp.length + 5)][2];
            for (int i = 0; i
                    < listOneTmp.length; i++) {
                listOne[i] = listOneTmp[i];
            }
            listOneTmp = null;
            listOneTmp = new String[listOne.length][2];
            listOne[n] = new String[2];
        }
        try {
            if (film.length() < 35 && actor.length() < 35) {
                listOne[n][0] = film;
                listOne[n][1] = actor;
                n++;
            } else throw new MyListException();
        } catch (MyListException e) {
            System.out.println("Too long names");
        }
    }

    public void deleteLine(String st) {
        int m;
        try {
            m = Integer.parseInt(st);
            if (m > 0 && m < (n + 1)) {
                listOne[m - 1] = null;
                n--;
                sortList();
            } else {
                System.out.println("Invalid line number\n");
            }
        } catch (NumberFormatException id1) {
            System.out.println("Invalid data format!\n");
        }
    }

    public void sortList() {
        for (int i = 0; i < listOne.length; i++) {
            for (int j = 0; j < listOne.length - 1 - i; j++) {
                if (listOne[j] == null) {
                    listOne[j] = listOne[j + 1];
                    listOne[j + 1] = null;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMovie collection");
        if (n == 0) {
            s.append("is Empty\n");
        }
        for (int i = 0; i < n; i++) {
            if (listOne[i] != null) {
                s.append("\n\n" + (i + 1) + " " + listOne[i][0] + ": " + listOne[i][1] + "\n");
            }
        }
        return s.toString();
    }
}