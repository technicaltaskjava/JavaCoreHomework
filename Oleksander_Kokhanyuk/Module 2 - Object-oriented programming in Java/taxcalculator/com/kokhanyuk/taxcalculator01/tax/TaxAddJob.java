/**
 * TaxAddJob
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class TaxAddJob extends Tax {

    public TaxAddJob(double Income, double taxRate) {
        super(Income, taxRate);
    }

    @Override
    public String getName() {
        return "Taxes from additional job: ";
    }
}
