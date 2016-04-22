package com.epam.task2;

import com.epam.task1.ControllerFindPrime;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 17.04.2016.
 */
public class Bank {
    protected String transactionsFileName;
    protected static final Logger log = Logger.getLogger(ControllerFindPrime.class);
    protected ArrayList<Account> accounts;
    protected LinkedList<Transaction> transactions;

    public Bank(int countAccounts, double initialBalance) {
        accounts = new ArrayList<>(countAccounts);
        for (int i = 0; i < countAccounts; i++) {
            accounts.add(new Account(i, initialBalance));
        }
    }

    public synchronized void transfer(int sender, int recipient, double amount) {
        while (accounts.get(sender).getBalance() < amount) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error(e);
                Thread.currentThread().interrupt();
            }
        }
        accounts.get(sender).changeBalance(-amount);
        accounts.get(recipient).changeBalance(amount);
        notifyAll();
    }

    public String readTextFromFile() {
        try (FileInputStream fileWords = new FileInputStream(transactionsFileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords);) {
            BufferedReader inFile = new BufferedReader(inputStreamReader);
            String currentLine;
            String textTransactions = "";
            while ((currentLine = inFile.readLine()) != null) {
                textTransactions += currentLine+"\n";
            }
            return textTransactions;
        } catch (IOException e) {
            log.error(e);
            return "";
        }
    }

    public List<Transaction> createTransaction(String textTransactions) {
        LinkedList<Transaction> listTransactions = new LinkedList<>();
        String[] lines = textTransactions.split("\n");
        for(String s : lines) {
            if((s != null) && !"".equals(s)) {
                String[] numbers = s.split("\t");
                int sender = Integer.parseInt(numbers[0]);
                int recipient = Integer.parseInt(numbers[1]);
                double amount = Double.parseDouble(numbers[2]);
                Transaction transaction = new Transaction(sender, recipient, amount);
                listTransactions.add(transaction);
            }
        }
        return listTransactions;
    }

    public void setTransactionsFileName(String transactionsFileName) {
        this.transactionsFileName = transactionsFileName;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = (LinkedList<Transaction>) transactions;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Transaction removeTransaction() {
        if(!transactions.isEmpty()) {
            return transactions.remove(0);
        } else {
            return null;
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getTotalBalance() {
        double totalBalance = 0;
        for(Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

}
