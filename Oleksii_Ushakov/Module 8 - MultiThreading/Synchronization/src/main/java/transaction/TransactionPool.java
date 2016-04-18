package transaction;

import org.xml.sax.SAXException;
import transaction.parser.TransactionParser;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class TransactionPool {
    private List<Transaction> transactionList;
    private boolean error = false;

    public TransactionPool() {
        this.transactionList = new LinkedList<>();
    }

    public void runActionXML(File xmlActionList) throws SAXException {
        TransactionParser parser = new TransactionParser();
        parser.parseTransaction(xmlActionList);

        transactionList = parser.getTransactionList();

        TransactionGroup transactionGroup = new TransactionGroup("Transaction group");
        transactionGroup.setDaemon(true);

        for (Transaction transaction : transactionList) {
            Thread thread = new Thread(transactionGroup, transaction);
            thread.start();
        }

        while (!transactionGroup.isDestroyed()) {
            error = transactionGroup.isError();
            /*waiting for threads*/
        }


    }

    public boolean isError() {
        return error;
    }
}
