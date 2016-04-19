package com.epam.task2;

        import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yuriy Krishtop on 18.04.2016.
 */
public class AccountTest {

    @Test
    public void accountTest() {
        Account account = new Account(34, 720);
        assertEquals(34, account.getId());
        assertEquals(0, Double.compare(720, account.getBalance()));
        account.changeBalance(-100);
        assertEquals(0, Double.compare(620, account.getBalance()));
    }
}
