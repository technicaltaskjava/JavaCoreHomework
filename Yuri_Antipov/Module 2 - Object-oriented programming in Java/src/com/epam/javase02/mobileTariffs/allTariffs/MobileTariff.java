package com.epam.javase02.mobileTariffs.allTariffs;

abstract public class MobileTariff {
    private String tariffName;
    private String serviceTypeAccount;
    private int costPerMonth;
    private int minutesPerMonth;
    private int megabytesPerMonth;
    private int smsAndMmsPerMonth;
    private int customerAmount;

    private double costPerMinutes; //in UAH
    private double costPerMegabytes;
    private double costPerSms;
    private double costPerMms;

    public MobileTariff(String tariffName, String serviceTypeAccount) {
        this.tariffName = tariffName;
        this.serviceTypeAccount = serviceTypeAccount;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
    public String getTariffName() {
        return tariffName;
    }

    public void setServiceTypeAccount(String serviceTypeAccount) {
        this.serviceTypeAccount = serviceTypeAccount;
    }
    public String getServiceTypeAccount() {
        return serviceTypeAccount;
    }

    public void setCostPerMonth(int costPerMonth) {
        this.costPerMonth = costPerMonth;
    }
    public int getCostPerMonth() {
        return costPerMonth = (int) (minutesPerMonth * costPerMinutes + megabytesPerMonth * costPerMegabytes +
                smsAndMmsPerMonth * costPerSms + smsAndMmsPerMonth * costPerMms);
    }

    public void setMinutesPerMonth(int minutesPerMonth) {
        this.minutesPerMonth = minutesPerMonth;
    }
    public int getMinutesPerMonth() {
        return minutesPerMonth;
    }

    public void setMegabytesPerMonth(int megabytesPerMonth) {
        this.megabytesPerMonth = megabytesPerMonth;
    }
    public int getMegabytesPerMonth() {
        return megabytesPerMonth;
    }

    public void setSmsAndMmsPerMonth(int smsAndMmsPerMonth) {
        this.smsAndMmsPerMonth = smsAndMmsPerMonth;
    }
    public int getSmsAndMmsPerMonth() {
        return smsAndMmsPerMonth;
    }

    public void setCustomerAmount(int customerAmount) {
        this.customerAmount = customerAmount;
    }
    public int getCustomerAmount() {
        return customerAmount;
    }

    public void setCostPerMinutes(double costPerMinutes) {
        this.costPerMinutes = costPerMinutes;
    }
    public double getCostPerMinutes() {
        return costPerMinutes;
    }

    public void setCostPerMegabytes(double costPerMegabytes) {
        this.costPerMegabytes = costPerMegabytes;
    }
    public double getCostPerMegabytes() {
        return costPerMegabytes;
    }

    public void setCostPerSms(double costPerSms) {
        this.costPerSms = costPerSms;
    }
    public double getCostPerSms() {
        return costPerSms;
    }

    public void setCostPerMms(double costPerMms) {
        this.costPerMms = costPerMms;
    }
    public double getCostPerMms() {
        return costPerMms;
    }
}
