package com.kokhanyuk.sql;

import com.kokhanyuk.sql.mypool.ConnectionPool;
import com.kokhanyuk.sql.mypool.FortuneCookies;
import com.kokhanyuk.sql.mypool.Searcher;
import org.apache.log4j.Logger;

/**
 * FortuneCookiesTwo
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FortuneCookiesTwo {
    static Logger log = Logger.getLogger(FortuneCookiesTwo.class);
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String COOKIES = "cookies";

    private FortuneCookiesTwo() {
    }

    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        connectionPool.setConnectionPool(DB_CONNECTION, DB_DRIVER, 5);

        Searcher[] searchUser = new Searcher[5];
        Searcher[] searchCookies = new Searcher[5];
        for (int i = 1; i < 5; i++) {
            searchUser[i] = new Searcher("user", i, "username");
            searchCookies[i] = new Searcher(COOKIES, i, "MESSAGE");
        }
        FortuneCookies us = new FortuneCookies();
        try {
            connectionPool.putback(us.addData(connectionPool.retrieve(), COOKIES, 15, "MESSAGE", "hello funny"));
            connectionPool.putback(us.removeData(connectionPool.retrieve(), COOKIES, 15));
        } catch (NullPointerException e) {
            log.warn(e.getMessage(), e);
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
