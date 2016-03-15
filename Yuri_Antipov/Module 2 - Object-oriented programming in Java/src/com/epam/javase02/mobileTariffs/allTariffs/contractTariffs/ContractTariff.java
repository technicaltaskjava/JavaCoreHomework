package com.epam.javase02.mobileTariffs.allTariffs.contractTariffs;

import com.epam.javase02.mobileTariffs.allTariffs.MobileTariff;

abstract public class ContractTariff extends MobileTariff {
    private boolean customerAgreement;

    public ContractTariff(String tariffName, String serviceTypeAccount) {
        super(tariffName, serviceTypeAccount);
    }

    public void setCustomerAgreement(boolean customerAgreement) {
        this.customerAgreement = customerAgreement;
    }
    public boolean isCustomerAgreement() {
        return customerAgreement;
    }
}
