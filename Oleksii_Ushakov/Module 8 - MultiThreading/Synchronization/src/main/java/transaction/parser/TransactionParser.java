package transaction.parser;

import account.Account;
import org.xml.sax.SAXException;
import transaction.Transaction;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class TransactionParser {
    private List<Transaction> transactionList;
    private List<Account> accountList;

    public TransactionParser() {
        this.transactionList = new LinkedList<>();
        this.accountList = new LinkedList<>();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Transaction> parseTransaction(File xmlFile) throws SAXException {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            TransactionParseHandler transactionParseHandler = new TransactionParseHandler();

            parser.parse(xmlFile, transactionParseHandler);

            transactionList = transactionParseHandler.getTransactionList();
            accountList = transactionParseHandler.getAccountList();

            return transactionList;

        } catch (ParserConfigurationException | IOException e) {
            throw new SAXException("Error while parsing " + xmlFile.getAbsolutePath(), e);
        }

    }
}
