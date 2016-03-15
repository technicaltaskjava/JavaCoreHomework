/**
 * DataPersonal
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.data;

public class DataPersonal {
    private String name, surname;
    private double mainWork, addJob, copyright, saleProperty, gift, transAbroad, finAssistanсe;
    private boolean children;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMainWork(double mainWork) {
        this.mainWork = mainWork;
    }

    public void setAddJob(double addJob) {
        this.addJob = addJob;
    }

    public void setCopyright(double copyright) {
        this.copyright = copyright;
    }

    public void setSaleProperty(double saleProperty) {
        this.saleProperty = saleProperty;
    }

    public void setGift(double gift) {
        this.gift = gift;
    }

    public void setTransAbroad(double transAbroad) {
        this.transAbroad = transAbroad;
    }

    public void setFinAssistanсe(double finAssistanсe) {
        this.finAssistanсe = finAssistanсe;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getMainWork() {
        return mainWork;
    }

    public double getAddJob() {
        return addJob;
    }

    public double getCopyright() {
        return copyright;
    }

    public double getSaleProperty() {
        return saleProperty;
    }

    public double getGift() {
        return gift;
    }

    public double getTransAbroad() {
        return transAbroad;
    }

    public double getFinAssistanсe() {
        return finAssistanсe;
    }

    public boolean isChildren() {
        return children;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nTax Calculator:-)\n\n");
        s.append(surname + " " + name + "\n" + Const.SET[2] + String.format("%.2f", mainWork) + "\n" + Const.SET[3]
                + String.format("%.2f", addJob) + "\n" + Const.SET[4] + String.format("%.2f", copyright) + "\n"
                + Const.SET[5] + String.format("%.2f", saleProperty) + "\n" + Const.SET[6] + String.format("%5.2f", gift)+"\n" + Const.SET[7]
                + String.format("%.2f", transAbroad) + "\n" + Const.SET[8] + String.format("%.2f", finAssistanсe)
                + "\n" + Const.SET[9] + children);
        return s.toString();
    }
}
