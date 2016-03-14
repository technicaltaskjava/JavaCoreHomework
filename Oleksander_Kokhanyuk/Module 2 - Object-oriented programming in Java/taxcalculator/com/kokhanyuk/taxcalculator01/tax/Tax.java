/**
 * Tax
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class Tax {
    protected double Income, taxRate, tax;

    public Tax() {
        this.Income = 0;
        this.taxRate = 0;
    }

    public Tax(double Income, double taxRate) {
        this.Income = Income;
        this.taxRate = taxRate;
    }

    public double calculateTax() {
        if (Income > 0 && taxRate > 0 && taxRate < 100) {
            tax = Income * (taxRate / 100);
            return tax;
        } else return 0;

    }

    public String getName() {
        return "Common taxes:";
    }

    @Override
    public String toString() {
        return Double.toString(tax);//
    }

}
