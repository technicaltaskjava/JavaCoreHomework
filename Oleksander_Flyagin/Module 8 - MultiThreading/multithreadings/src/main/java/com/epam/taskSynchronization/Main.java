package com.epam.taskSynchronization;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        GetInformationTranc getInf = new GetInformationTranc();
        getInf.getProperties();
        // Account account1 = new Account(getInf.getAccauntName1(),50); // NOSONAR
        Account account1 = new Account(getInf.getAccauntName1());
        Account account2 = new Account(getInf.getAccauntName2());

        StartOperation startOperation = new StartOperation();
        startOperation.trancsaction(account1, account2, getInf.getDeposit());
    }
}
