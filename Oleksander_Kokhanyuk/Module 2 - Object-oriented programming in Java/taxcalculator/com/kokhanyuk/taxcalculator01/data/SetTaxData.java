/**
 * SetTaxData
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.data;

import com.kokhanyuk.taxcalculator01.tax.*;

public class SetTaxData {
    public static void setTax(DataPersonal d1, DataTaxPersonal dt1) {
        dt1.setNameTax(d1.getSurname(), d1.getName());
        TaxMainWork tW = new TaxMainWork(d1.getMainWork(), 30, d1.isChildren());
        dt1.setTax(tW);
        TaxAddJob tA = new TaxAddJob(d1.getAddJob(), 45);
        dt1.setTax(tA);
        TaxCopyright tC = new TaxCopyright(d1.getCopyright(), 12.5);
        dt1.setTax(tC);
        TaxSaleProperty tS = new TaxSaleProperty(d1.getSaleProperty(), 55.8);
        dt1.setTax(tS);
        TaxGift fG=new TaxGift(d1.getGift(),25);
        dt1.setTax(fG);
        TaxTransAbroad tT = new TaxTransAbroad(d1.getTransAbroad(), 5);
        dt1.setTax(tT);
        TaxFinAssistance tF = new TaxFinAssistance(d1.getFinAssistanсe(), 18.1);
        dt1.setTax(tF);
    }
}
