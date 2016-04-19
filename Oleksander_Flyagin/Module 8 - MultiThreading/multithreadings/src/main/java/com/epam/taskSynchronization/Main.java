package com.epam.taskSynchronization;

public class Main {
    public static void main(String[] args) {
        GetInformationTranc getInf = new GetInformationTranc();
        getInf.getProperties();
        // Account account1 = new Account(getInf.getAccauntName1(),50);
        Account account1 = new Account(getInf.getAccauntName1());
        Account account2 = new Account(getInf.getAccauntName2());

        StartOperation startOperation = new StartOperation();
        startOperation.trancsaction(account1, account2, getInf.getDeposit());
    }
}
