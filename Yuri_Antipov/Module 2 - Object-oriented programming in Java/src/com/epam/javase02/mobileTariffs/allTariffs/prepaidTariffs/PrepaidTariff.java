package com.epam.javase02.mobileTariffs.allTariffs.prepaidTariffs;

import com.epam.javase02.mobileTariffs.allTariffs.MobileTariff;

public class PrepaidTariff extends MobileTariff {
    public PrepaidTariff(String tariffName, String serviceTypeAccount) {
        super(tariffName, serviceTypeAccount);
        setCostPerMinutes(0.17);
        setCostPerMegabytes(0.01);
        setCostPerSms(0.1);
        setCostPerMms(0.35);
    }
}
