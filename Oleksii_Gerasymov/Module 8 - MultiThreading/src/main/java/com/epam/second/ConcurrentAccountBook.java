package com.epam.second;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentAccountBook {
    private List<Account> accounts;

    public ConcurrentAccountBook() {
        this.accounts = new CopyOnWriteArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(int currentAccount) {
        return accounts.get(currentAccount);
    }

    public int getNumberOfAccounts(){
        return this.accounts.size();
    }

    public void addAccount(Account currentAccount) {
        this.accounts.add(currentAccount);
    }
}
