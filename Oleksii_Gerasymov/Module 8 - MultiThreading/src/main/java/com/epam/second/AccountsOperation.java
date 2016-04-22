package com.epam.second;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AccountsOperation {
    private static Logger log = Logger.getLogger(AccountsOperation.class.getName());

    private AccountsOperation() {
    }

    public static void mainMenu(String accountsLog, String beginningBalance, boolean isConcurrency) {
        try {
            List<String> withdrawAccountList = new ArrayList<>();
            List<String> depositAccountList = new ArrayList<>();
            List<Integer> withdrawSumList = new ArrayList<>();
            List<Integer> depositSumList = new ArrayList<>();
            AccountBook accountBook = new AccountBook();
            ConcurrentAccountBook concurrentAccountBook = new ConcurrentAccountBook();

            createBeginningBalance(beginningBalance, accountBook, concurrentAccountBook);
            createCashflowList(accountsLog, accountBook, withdrawAccountList, depositAccountList,
                    withdrawSumList, depositSumList);
            if (isConcurrency) {
                makeConcurrentBalance(concurrentAccountBook, withdrawAccountList, depositAccountList,
                        withdrawSumList, depositSumList);
            }
            else {
                makeSynchronizedBalance(accountBook, withdrawAccountList, depositAccountList,
                        withdrawSumList, depositSumList);
            }
        }
        catch (FileNotFoundException | InterruptedException userException) {
            log.info(String.valueOf(userException));
        }
    }

    public static void createBeginningBalance(String beginningBalance, AccountBook accountBook,
                                 ConcurrentAccountBook concurrentAccountBook) throws FileNotFoundException {
        Scanner inputData = new Scanner(new FileInputStream(beginningBalance));
        while (inputData.hasNext()) {
            String[] currentLine = inputData.nextLine().split(" ");
            String currentNumberOfAccount = currentLine[0];
            Integer currentBalance = Integer.valueOf(currentLine[1]);
            Account currentAccount = new Account(currentNumberOfAccount, currentBalance);
            accountBook.addAccount(currentAccount);
            concurrentAccountBook.addAccount(currentAccount);
        }
        inputData.close();
        System.out.println(accountBook.getNumberOfAccounts() + " accounts with beginning balance loaded :");
        for (Account currentAccount : accountBook.getAccounts()) {
            System.out.println(currentAccount.outAccountData());
        }
    }

    public static void createCashflowList(String accountsLog, AccountBook accountBook,
                                           List<String> withdrawAccountList,
                                           List<String> depositAccountList,
                                           List<Integer> withdrawSumList,
                                           List<Integer> depositSumList) throws FileNotFoundException {
        Scanner inputData = new Scanner(new FileInputStream(accountsLog));
        while (inputData.hasNext()) {
            String[] currentLine = inputData.nextLine().split(" ");
            String currentSender = currentLine[0];
            String currentReceiver = currentLine[1];
            Integer currentSum = Integer.valueOf(currentLine[2]);
            for (int accountIndex = 0; accountIndex < accountBook.getNumberOfAccounts(); accountIndex++) {
                if (accountBook.getAccount(accountIndex).getNumberOfAccount().equals(currentSender)) {
                    withdrawAccountList.add(currentSender);
                    withdrawSumList.add(currentSum);
                }
                if (accountBook.getAccount(accountIndex).getNumberOfAccount().equals(currentReceiver)) {
                    depositAccountList.add(currentReceiver);
                    depositSumList.add(currentSum);
                }
            }
        }
        inputData.close();
    }

    public static void makeSynchronizedBalance(AccountBook accountBook,
                                                List<String> withdrawAccountList,
                                                List<String> depositAccountList,
                                                List<Integer> withdrawSumList,
                                                List<Integer> depositSumList) throws InterruptedException {
        DepositThread depositThread = new DepositThread(accountBook, depositAccountList, depositSumList);
        WithdrawThread withdrawThread = new WithdrawThread(accountBook, withdrawAccountList, withdrawSumList);
        depositThread.start();
        withdrawThread.start();
        depositThread.join();
        withdrawThread.join();
        for (int accountIndex = 0; accountIndex < accountBook.getNumberOfAccounts(); accountIndex++) {
            System.out.print("Account " + accountBook.getAccount(accountIndex).getNumberOfAccount());
            System.out.println(" has end balance " + accountBook.getAccount(accountIndex).getBalance());
        }
    }

    public static void makeConcurrentBalance(ConcurrentAccountBook concurrentAccountBook,
                                              List<String> withdrawAccountList,
                                              List<String> depositAccountList,
                                              List<Integer> withdrawSumList,
                                              List<Integer> depositSumList) throws InterruptedException {
        ConcurrentDepositThread concurrentDepositThread = new ConcurrentDepositThread(concurrentAccountBook,
                depositAccountList, depositSumList);
        ConcurrentWithdrawThread concurrentWithdrawThread = new ConcurrentWithdrawThread(concurrentAccountBook,
                withdrawAccountList, withdrawSumList);
        concurrentDepositThread.start();
        concurrentWithdrawThread.start();
        concurrentDepositThread.join();
        concurrentWithdrawThread.join();
        for (int accountIndex = 0; accountIndex < concurrentAccountBook.getNumberOfAccounts(); accountIndex++) {
            System.out.print("Account " + concurrentAccountBook.getAccount(accountIndex).getNumberOfAccount());
            System.out.println(" has end balance " + concurrentAccountBook.getAccount(accountIndex).getBalance());
        }
    }
}
