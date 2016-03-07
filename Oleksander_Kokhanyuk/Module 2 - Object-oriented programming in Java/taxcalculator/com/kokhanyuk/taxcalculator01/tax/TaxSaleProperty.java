/**
 * TaxSaleProperty
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class TaxSaleProperty extends Tax {
    public TaxSaleProperty(double Income, double taxRate) {
        super(Income, taxRate);
    }

    @Override
    public String getName() {
        return "Taxes from the sale of property: ";
    }
}
