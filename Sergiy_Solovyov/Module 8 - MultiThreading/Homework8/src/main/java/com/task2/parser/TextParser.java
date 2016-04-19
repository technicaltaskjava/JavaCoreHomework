package com.task2.parser;

import com.task2.entity.Account;
import com.task2.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Sergey Solovyov
 */
public class TextParser {

    private  Queue<Transaction> transactionList = new ConcurrentLinkedQueue<>();
    public static Map<String, Account> accounts = new HashMap<>(); //NOSONAR
    private static final Logger LOGGER = LoggerFactory.getLogger(TextParser.class);

    public void loadTransactions(String fileName){

        File file = new File(fileName);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));){

            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                parseTransaction(currentLine);
            }

        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
    public void loadAccounts(String fileName){

        File file = new File(fileName);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));){

            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                parseAccount(currentLine);
            }

        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
    private void parseTransaction(String transaction){
        String [] arr = transaction.split(" ");
        transactionList.add(new Transaction(arr[0],arr[1],Integer.valueOf(arr[2])));
    }
    private void parseAccount(String transaction){
        String [] arr = transaction.split(" ");
        accounts.put(arr[0], new Account(arr[0],Integer.valueOf(arr[1])));
    }

    public Queue<Transaction> getTransactionList() {
        return transactionList;
    }

}
