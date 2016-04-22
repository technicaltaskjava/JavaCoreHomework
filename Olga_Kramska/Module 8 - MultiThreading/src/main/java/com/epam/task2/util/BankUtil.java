package com.epam.task2.util;

import com.epam.task2.model.TransferInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */

public class BankUtil {
    private BankUtil() {
    }

    public static List<TransferInfo> readTransferInfoFromFile(String fileName) {
        List<TransferInfo> transfersInfo = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] transferInfo = line.split("\t");
                    transfersInfo.add(new TransferInfo(Integer.parseInt(transferInfo[0]), Integer.parseInt(transferInfo[1]),
                            new BigDecimal(transferInfo[2])));
                }
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can not read file: " + fileName, ex);
        }

        return transfersInfo;
    }
}
