package com.kokhanyuk.collections.nombersstorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RunNumber
 *
 * This class used NumbersStoradge.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class RunNumber {

    Logger log = LoggerFactory.getLogger(RunNumber.class);

    public void number() {
        int n = 20;
        int tmp1 = 0;
        int j = 0;
        int tmp2;
        Number rez = 0;

        NumbersStorage num = new NumbersStorage();
        for (int i = 0; i < 20; i++) {
            num.addNumber(Math.round(Math.random() * 100));
        }
        log.info(String.valueOf(num));
        for (int i = 0; i < num.getSize(); i++) {
            if (Integer.parseInt(String.valueOf(num.getNumber(i))) > tmp1) {
                tmp1 = Integer.parseInt(String.valueOf(num.getNumber(i)));
            }
        }

        for (int i = 0; i < num.getSize(); i++) {
            tmp2 = Math.abs(n - Integer.parseInt(String.valueOf(num.getNumber(i))));
            if (tmp2 < tmp1) {
                tmp1 = tmp2;
                rez = num.getNumber(i);
                j = i;
            }
        }
        log.info("On rezult place: " + rez + " will be inserted 101.");
        num.addNumber(j, 101);
        log.info(String.valueOf(num));
        log.info("And rezult: " + rez + " will removed.");
        num.remNumber((Number) rez);
        log.info(String.valueOf(num));
    }
}
