/**
 * DataTaxPersonal
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.data;

import com.kokhanyuk.taxcalculator01.tax.Tax;

public class DataTaxPersonal {
    int i = 0;
    private Tax[] arrTax = new Tax[7];
    private String surname, name;

    public void setNameTax(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public void setTax(Tax t1) {
        if (i < arrTax.length) {
            arrTax[i] = t1;
            i++;
        }
    }

    public void sortArrTaxUp() {
        Tax tmp;
        for (int i = 0; i < arrTax.length; i++) {
            for (int j = 0; j < arrTax.length - 1 - i; j++) {
                if (arrTax[j].calculateTax() < arrTax[j + 1].calculateTax()) {
                    tmp = arrTax[j];
                    arrTax[j] = arrTax[j + 1];
                    arrTax[j + 1] = tmp;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nTaxes " + surname + " " + name + "\n");
        String formattedDouble;
        for (int j = 0; j < 7; j++) {
            formattedDouble = String.format("%.2f", arrTax[j].calculateTax());
            s.append(arrTax[j].getName());
            s.append(formattedDouble + "\n");
        }
        return s.toString();
    }
}



