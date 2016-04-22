package com.epam;

import com.epam.second.AccountBook;
import com.epam.second.AccountsOperation;
import com.epam.second.ConcurrentAccountBook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

public class AccountsTest {
    private static Logger log = Logger.getLogger(NumberFinderTest.class.getName());

    @Test
    public void balanceTest() {
        List<String> withdrawAccountList = new ArrayList<>();
        List<String> depositAccountList = new ArrayList<>();
        List<Integer> withdrawSumList = new ArrayList<>();
        List<Integer> depositSumList = new ArrayList<>();
        AccountBook accountBook = new AccountBook();
        ConcurrentAccountBook concurrentAccountBook = new ConcurrentAccountBook();
        try {
            AccountsOperation.createBeginningBalance("src/main/resources/balance.txt", accountBook,
                    concurrentAccountBook);
            AccountsOperation.createCashflowList("src/main/resources/cashflow.txt", accountBook, withdrawAccountList,
                    depositAccountList, withdrawSumList, depositSumList);
            AccountsOperation.makeSynchronizedBalance(accountBook, withdrawAccountList, depositAccountList,
                    withdrawSumList, depositSumList);
        }
        catch (FileNotFoundException userException) {
            log.info(String.valueOf(userException));
        }
        catch (InterruptedException userException) {
            Thread.currentThread().interrupt();
        }
        assertEquals(5800, accountBook.getAccount(0).getBalance());
        assertEquals(25600, accountBook.getAccount(1).getBalance());
    }
}
