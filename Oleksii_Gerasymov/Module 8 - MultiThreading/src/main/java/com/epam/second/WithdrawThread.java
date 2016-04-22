package com.epam.second;

import java.util.List;

public class WithdrawThread extends Thread {
    private List<String> withdrawAccountList;
    private List<Integer> withdrawSumList;
    AccountBook accountBook;

    public WithdrawThread(AccountBook accountBook, List<String> withdrawAccountList, List<Integer> withdrawSumList) {
        this.accountBook = accountBook;
        this.withdrawAccountList = withdrawAccountList;
        this.withdrawSumList = withdrawSumList;
    }

    @Override
    public void run() {
        for (int currentWithdraw = 0; currentWithdraw < withdrawAccountList.size(); currentWithdraw++) {
            for (int accountIndex = 0; accountIndex < accountBook.getNumberOfAccounts();
                 accountIndex++) {
                if (accountBook.getAccount(accountIndex).getNumberOfAccount().
                        equals(withdrawAccountList.get(currentWithdraw))) {
                    accountBook.getAccount(accountIndex).synchronizedWithdraw(withdrawSumList.get(currentWithdraw));
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
