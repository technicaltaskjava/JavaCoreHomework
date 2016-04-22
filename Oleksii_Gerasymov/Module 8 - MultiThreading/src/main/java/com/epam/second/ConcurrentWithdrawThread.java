package com.epam.second;

import java.util.List;

public class ConcurrentWithdrawThread extends Thread {
    private List<String> withdrawAccountList;
    private List<Integer> withdrawSumList;
    ConcurrentAccountBook concurrentAccountBook;

    public ConcurrentWithdrawThread(ConcurrentAccountBook concurrentAccountBook, List<String> withdrawAccountList,
                                    List<Integer> withdrawSumList) {
        this.concurrentAccountBook = concurrentAccountBook;
        this.withdrawAccountList = withdrawAccountList;
        this.withdrawSumList = withdrawSumList;
    }

    @Override
    public void run() {
        for (int currentWithdraw = 0; currentWithdraw < withdrawAccountList.size(); currentWithdraw++) {
            for (int accountIndex = 0; accountIndex < concurrentAccountBook.getNumberOfAccounts();
                 accountIndex++) {
                if (concurrentAccountBook.getAccount(accountIndex).getNumberOfAccount().
                        equals(withdrawAccountList.get(currentWithdraw))) {
                    concurrentAccountBook.getAccount(accountIndex).concurrentWithdraw(withdrawSumList.get(currentWithdraw));
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
