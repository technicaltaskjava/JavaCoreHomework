package transaction.parser;

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

    public TransactionParser() {
        this.transactionList = new LinkedList<>();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public List<Transaction> parseTransaction(File xmlFile) throws SAXException {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            TransactionParseHandler transactionParseHandler = new TransactionParseHandler();

            parser.parse(xmlFile, transactionParseHandler);

            transactionList = transactionParseHandler.getTransactionList();

            return transactionList;

        } catch (ParserConfigurationException | IOException e) {
            throw new SAXException("Error while parsing " + xmlFile.getAbsolutePath(), e);
        }

    }
}
