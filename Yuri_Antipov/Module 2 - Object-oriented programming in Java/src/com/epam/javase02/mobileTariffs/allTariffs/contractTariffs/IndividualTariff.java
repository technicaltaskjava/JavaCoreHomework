package com.epam.javase02.mobileTariffs.allTariffs.contractTariffs;

public class IndividualTariff extends ContractTariff{
    public IndividualTariff(String nameTariff, String serviceTypeAccount) {
        super(nameTariff, serviceTypeAccount);
        setCustomerAgreement(true);
        setCostPerMinutes(0.14);
        setCostPerMegabytes(0.009);
        setCostPerSms(0.09);
        setCostPerMms(0.31);
    }
}
