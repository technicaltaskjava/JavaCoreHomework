package com.epam.javase02.mobileTariffs.allTariffs.contractTariffs;

public class BusinessTariff extends ContractTariff {
    public BusinessTariff(String tariffName, String serviceTypeAccount) {
        super(tariffName,serviceTypeAccount);
        setCustomerAgreement(true);
        setCostPerMinutes(0.08);
        setCostPerMegabytes(0.005);
        setCostPerSms(0.05);
        setCostPerMms(0.27);
    }
}
