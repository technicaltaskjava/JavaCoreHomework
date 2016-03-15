/**
 * TaxGift
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.tax;

public class TaxGift extends Tax {
    public TaxGift(double Income, double taxRate) {
        super(Income, taxRate);
    }

    @Override
    public String getName() {
        return "Taxes from gift: ";
    }
}
