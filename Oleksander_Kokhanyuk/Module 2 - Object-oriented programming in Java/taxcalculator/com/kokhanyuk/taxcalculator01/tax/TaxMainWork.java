/**
 * TaxMainWork
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class TaxMainWork extends Tax {
    private boolean children;

    public TaxMainWork(double Income, double taxRate, boolean children) {
        this.Income = Income;
        this.taxRate = taxRate;
        this.children = children;

    }

    @Override
    public double calculateTax() {
        if ((Income > 0) && (taxRate > 0) && (taxRate < 100) && children) {
            if (Income > 1250) {
                tax = (Income - 12_500) * (taxRate/5 / 100);
            } else return 0;
        } else {
            tax = Income * (taxRate / 100);
        }
        return tax;
    }

    @Override
    public String getName() {
        return "Taxes from main of work: ";
    }

}
