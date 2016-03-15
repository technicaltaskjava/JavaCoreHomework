/**
 * TaxFinAssistance
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class TaxFinAssistance extends Tax {

    public TaxFinAssistance(double Income, double taxRate) {
        super(Income, taxRate);
    }

    @Override
    public String getName() {
        return "Taxes from financial assistance: ";
    }
}
