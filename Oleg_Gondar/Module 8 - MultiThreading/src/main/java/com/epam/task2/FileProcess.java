package com.epam.task2;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.List;


/**
 * Created by Oleg on 20.04.2016.
 */
public class FileProcess implements Runnable {

    private int startLine;
    private int endLine;
    private Account account1;
    private Account account2;
    private List<String> accountOperationsData;
    boolean isConcurrentOperation;
    private static final Logger logger = Logger.getLogger(String.valueOf(FileProcess.class));

    public FileProcess(Account account1, Account account2, String fileName, int startLine, int endLine, boolean isConcurrentOperation) {
        this.account1 = account1;
        this.account2 = account2;
        this.startLine = startLine;
        this.endLine = endLine;
        this.isConcurrentOperation = isConcurrentOperation;
        try {
            accountOperationsData = AccountFileReader.getLines(fileName);
        } catch (FileNotFoundException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
    }

    @Override
    public void run() {
        processLines();
    }

    public void processLines() {
        for (int i = startLine; i <= endLine; i++) {
            String[] s = accountOperationsData.get(i - 1).split("\\s");
            performOperation(s[0], Integer.valueOf(s[2]));
        }
    }

    private void performOperation(String account, int summ) {
        if (isConcurrentOperation) {
            new AccountOperator(account1, account2).performConcurrentOperation(account, summ);
        } else {
            new AccountOperator(account1, account2).performSynchronizedOperation(account, summ);
        }
    }
}

