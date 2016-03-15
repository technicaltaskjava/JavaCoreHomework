package com.epam.javase02.mobileTariffs.printer;

import com.epam.javase02.mobileTariffs.allTariffs.MobileTariff;

public class PrintResult {
    public void printListTariff(MobileTariff[] tariff) {
        System.out.println("There are the tariffs:");
        System.out.print("+");
        for (int j = 0; j < 7; j++) {
            System.out.print("-------------------+");
        }
        System.out.println();
        System.out.println("|\tTariff name/\t|\t Minutes/\t\t|\t Megabytes/\t\t|\t\tSMS/\t\t|\t\tMMS/\t\t|\t\tCost\t\t|\tCustomer amount\t|");
        System.out.println("|\ttype account\t| cost per minutes\t| cost per megabytes|\tcost per SMS\t|\tcost per MMS\t|\tof license fee\t|\t\t\t\t\t|");
        System.out.print("+");
        for (int j = 0; j < 7; j++) {
            System.out.print("-------------------+");
        }
        System.out.println();
        String name, minutes, megabytes, sms, mms, cost, custAmount;
        String type, costMin, costMgbs, costSms, costMms;
        for (int i = 0; i < tariff.length; i++) {
            name = tariff[i].getTariffName();
            minutes = String.format("%16d",tariff[i].getMinutesPerMonth());
            megabytes = String.format("%18d",tariff[i].getMegabytesPerMonth());
            sms = String.format("%16d",tariff[i].getSmsAndMmsPerMonth());
            mms = String.format("%16d",tariff[i].getSmsAndMmsPerMonth());
            cost = String.format("%16d",tariff[i].getCostPerMonth());
            custAmount = String.format("%15d",tariff[i].getCustomerAmount());
            System.out.println("\t" + name + "\t" + minutes + "\t"  + megabytes + "\t"  + sms + "\t"  + mms + "\t"  + cost + " UAH\t" + custAmount);

            type = tariff[i].getServiceTypeAccount();
            costMin = String.format("%14.3f UAH",tariff[i].getCostPerMinutes());
            costMgbs = String.format("%14.3f UAH",tariff[i].getCostPerMegabytes());
            costSms = String.format("%14.3f UAH",tariff[i].getCostPerSms());
            costMms = String.format("%14.3f UAH",tariff[i].getCostPerMms());

            System.out.println("\t" + type + "   \t" + costMin + "\t"  + costMgbs + "\t"  + costSms + "\t"  + costMms);
            /* System.out.println("\t" + tariff[i].getServiceTypeAccount() + "\t\t\t  " + tariff[i].getCostPerMinutes() +
                    " UAH\t\t\t\t" + tariff[i].getCostPerMegabytes() + " UAH\t\t  " + tariff[i].getCostPerSms() + " UAH\t\t\t\t" +
                    tariff[i].getCostPerMms() + " UAH\t\t\t\t\t\t");*/
            System.out.print("+");
            for (int j = 0; j < 7; j++) {
                System.out.print("-------------------+");
            }
            System.out.println();
        }
    }

    public void printUserTariff(MobileTariff tariff) {
        System.out.println("You created " + tariff.getServiceTypeAccount() + " tariff \""+ tariff.getTariffName() + "\".");
        System.out.println("Cost of license fee is " + tariff.getCostPerMonth() + " UAH per month.");
        System.out.println("You get (per month):");
        System.out.println("unlimited calls within native network,");
        System.out.println(tariff.getMinutesPerMonth() + " minutes to call to other network,");
        System.out.println(tariff.getMegabytesPerMonth() + " megabytes of Internet,");
        System.out.println(tariff.getSmsAndMmsPerMonth() + " SMS and MMS on the whole territory of country.");
        System.out.println();
    }
}
