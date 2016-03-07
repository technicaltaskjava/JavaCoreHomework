/**
 * TaxTransAbroad
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class TaxTransAbroad extends Tax {

    public TaxTransAbroad(double Income, double taxRate) {
        super(Income, taxRate);
    }

    @Override
    public String getName() {
        return "Taxes from transfers from abroad: ";
    }
}
