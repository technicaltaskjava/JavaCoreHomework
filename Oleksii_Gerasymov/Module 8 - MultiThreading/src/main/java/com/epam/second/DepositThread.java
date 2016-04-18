package com.epam.second;

import java.util.List;

public class DepositThread extends Thread {
    private List<String> depositAccountList;
    private List<Integer> depositSumList;
    AccountBook accountBook;

    public DepositThread(AccountBook accountBook, List<String> depositAccountList, List<Integer> depositSumList) {
        this.accountBook = accountBook;
        this.depositAccountList = depositAccountList;
        this.depositSumList = depositSumList;
    }

    @Override
    public void run() {
        for (int currentDeposit = 0; currentDeposit < depositAccountList.size(); currentDeposit++) {
            for (int accountIndex = 0; accountIndex < accountBook.getNumberOfAccounts();
                 accountIndex++) {
                if (accountBook.getAccount(accountIndex).getNumberOfAccount().
                        equals(depositAccountList.get(currentDeposit))) {
                    accountBook.getAccount(accountIndex).synchronizedDeposit(depositSumList.get(currentDeposit));
                }
            }
        }
        try {
            sleep(500);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}