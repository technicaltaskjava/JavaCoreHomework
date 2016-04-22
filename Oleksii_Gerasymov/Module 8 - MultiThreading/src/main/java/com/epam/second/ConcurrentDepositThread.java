package com.epam.second;

import java.util.List;

public class ConcurrentDepositThread extends Thread {
    private List<String> depositAccountList;
    private List<Integer> depositSumList;
    ConcurrentAccountBook concurrentAccountBook;

    public ConcurrentDepositThread(ConcurrentAccountBook concurrentAccountBook, List<String> depositAccountList,
                                   List<Integer> depositSumList) {
        this.concurrentAccountBook = concurrentAccountBook;
        this.depositAccountList = depositAccountList;
        this.depositSumList = depositSumList;
    }

    @Override
    public void run() {
        for (int currentDeposit = 0; currentDeposit < depositAccountList.size(); currentDeposit++) {
            for (int accountIndex = 0; accountIndex < concurrentAccountBook.getNumberOfAccounts();
                 accountIndex++) {
                if (concurrentAccountBook.getAccount(accountIndex).getNumberOfAccount().
                        equals(depositAccountList.get(currentDeposit))) {
                    concurrentAccountBook.getAccount(accountIndex).concurrentDeposit(depositSumList.get(currentDeposit));
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
