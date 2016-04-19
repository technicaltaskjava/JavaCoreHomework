package com.epam.taskSynchronization;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class GetInformationTranc {
    private Logger myLog = Logger.getLogger("Loger");
    private int deposit;
    private String accauntName1;
    private String accauntName2;

    public int getDeposit() {
        return deposit;
    }

    public String getAccauntName2() {
        return accauntName2;
    }

    public String getAccauntName1() {
        return accauntName1;
    }


    public void getProperties() {
        FileInputStream read;
        Properties property = new Properties();

        try {
            read = new FileInputStream("src\\main\\java\\com\\epam\\taskSynchronization\\transaction.properties");
            property.load(read);
            accauntName1 = property.getProperty("account1");
            accauntName2 = property.getProperty("account2");
            deposit = Integer.parseInt(property.getProperty("put"));

        } catch (IOException e) {
            myLog.warning("WORNING, file not found" + e);
        }

    }


}
