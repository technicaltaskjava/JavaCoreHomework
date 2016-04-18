package com.epam.second;

import java.util.ArrayList;
import java.util.List;

public class AccountBook {
    private List<Account> accounts;

    public AccountBook() {
        this.accounts = new ArrayList<>();
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
