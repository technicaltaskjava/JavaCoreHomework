package javase.t02.transactions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Transactions XML Parser Class (method DOM)
 * Created by Yury Vislobodsky on 12.04.2016.
 */
public class TransactionsParser {
    private TransactionsParser() {}

    public static BlockingQueue<Transaction> createFromXml(String fileName) throws TransactionsParserException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(fileName));
            Element root = document.getDocumentElement();
            NodeList transactionNodes = root.getElementsByTagName("transaction");

            BlockingQueue<Transaction> transactions = new LinkedBlockingQueue<>();
            for (int i = 0; i < transactionNodes.getLength(); i++) {
                Element transactionElement = (Element) transactionNodes.item(i);
                int accountIdFrom = Integer.parseInt(getMultiChild(transactionElement, "accountIdFrom")[0]);
                int accountIdTo = Integer.parseInt(getMultiChild(transactionElement, "accountIdTo")[0]);
                int sum = Integer.parseInt(getMultiChild(transactionElement, "sum")[0]);
                Transaction transaction = new Transaction(accountIdFrom, accountIdTo, sum);
                transactions.offer(transaction);
            }
            return transactions;

        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new TransactionsParserException(e);
        }
    }

    private static String[] getMultiChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        String[] lines = new String[nodeList.getLength()];
        for (int index = 0; index < nodeList.getLength(); index++) {
            lines[index]  = nodeList.item(index).getTextContent().trim();
        }
        return lines;
    }
}
