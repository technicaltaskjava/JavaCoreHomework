/**
 * TaxCalculator01
 * Calculate personal taxes
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01;

import com.kokhanyuk.taxcalculator01.data.*;
import java.util.Scanner;

public class TaxCalculator01 {

    public static void main(String[] args) {

        int n = 0;
        String st, s;

        System.out.println("\nTax calculator\n");
        Scanner in = new Scanner(System.in);
        while (n != 4) {
            System.out.println("1-Run example;\n2-Entering data on command line;\n3-Read data from file;\n4-Exit.");
            st = in.nextLine();
            try {
                n = Integer.parseInt(st);
                switch (n) {
                    case 1:
                        DataPersonal pers1 = new DataPersonal();
                        DataTaxPersonal dt1 = new DataTaxPersonal();
                        SetDataPersonal.setExampleData(pers1);
                        System.out.println(pers1);
                        SetTaxData.setTax(pers1, dt1);
                        dt1.sortArrTaxUp();
                        System.out.println(dt1);
                        break;
                    case 2:
                        DataPersonal pers2 = new DataPersonal();
                        DataTaxPersonal dt2 = new DataTaxPersonal();
                        for (int i = 0; i < Const.SET.length; i++) {
                            s = Const.SET[i];
                            System.out.println("\n" + s);
                            st = in.nextLine();
                            if (!SetDataPersonal.setData(pers2, i, st)) i--;
                        }
                        System.out.println(pers2);
                        SetTaxData.setTax(pers2, dt2);
                        dt2.sortArrTaxUp();
                        System.out.println(dt2);
                        break;
                    case 3:
                        DataPersonal pers3 = new DataPersonal();
                        DataTaxPersonal dt3 = new DataTaxPersonal();
                        DataReaderFile file = new DataReaderFile();
                        String[] arrDataFile = file.getDataFile(Const.SET.length);
                        int i = 0;
                        for (String s2 : arrDataFile) {
                            SetDataPersonal.setData(pers3, i, s2);
                            i++;
                        }
                        System.out.println(pers3);
                        SetTaxData.setTax(pers3, dt3);
                        dt3.sortArrTaxUp();
                        System.out.println(dt3);
                        break;
                    default:
                        n = 4;
                }
            } catch (NumberFormatException id1) {
                System.out.println("Invalid data format!");
            }
        }
        in.close();
    }
}
