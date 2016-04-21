package transaction.parser;

import account.Account;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import transaction.Transaction;
import transaction.parser.util.TransactionXMLTag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Alexey Ushakov
 */
public class TransactionParseHandler extends DefaultHandler {
    private List<Transaction> transactionList;
    private Map<String, Account> accountMap;
    private String checkedTag;
    private Account sender;
    private Account recipient;
    private int value;


    public TransactionParseHandler() {
        super();
        this.transactionList = new LinkedList<>();
        this.accountMap = new HashMap<>();
        this.checkedTag = "";
    }

    public List<Account> getAccountList() {
        return new LinkedList<>(accountMap.values());
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        checkedTag = "";
        if (TransactionXMLTag.getTag(qName) == TransactionXMLTag.TRANSACTION) {
            transactionList.add(new Transaction(sender, recipient, value));
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        checkedTag = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String tagValue = new String(ch, start, length);
        TransactionXMLTag tag = TransactionXMLTag.getTag(checkedTag);

        switch (tag) {
            case VALUE:
                value = Integer.valueOf(tagValue);
                break;

            case SENDER:
                sender = getAccount(tagValue);
                break;

            case RECIPIENT:
                recipient = getAccount(tagValue);
                break;

            default:
                break;
        }


    }

    private Account getAccount(String id) {
        if (!accountMap.containsKey(id)) {
            accountMap.put(id, new Account(5000, id));
        }
        return accountMap.get(id);
    }


}
