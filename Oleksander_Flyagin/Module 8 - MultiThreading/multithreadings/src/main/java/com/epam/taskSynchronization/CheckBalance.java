package com.epam.taskSynchronization;


import java.util.Scanner;

public class CheckBalance implements Runnable {
    Account account = null;

    private int deposit;
    public CheckBalance(Account account, int deposit) {
        this.account = account;
        this.deposit = deposit;
        Thread thread = new Thread(this);
        thread.isDaemon();
    }

    @Override
    public void run() {

        while (!account.balanceStatus) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пополните счет для проведени операции\r\nвведите сумму для пополнения");
            int setMoney = scanner.nextInt();
            if (setMoney < deposit) {
                System.out.println("вы пополнели на " + setMoney);
                System.out.println("недостаточно средств, нехватает " + (deposit - setMoney));
            } else {
                account.deposit(setMoney);
                account.balanceStatus = true;
                account.withdraw(deposit);
            }
        }

    }


}



