package view;

import config.Log4jConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;
import transaction.TransactionPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Alexey Ushakov
 */
public class TransactionMenu {

    private static final Logger logger = Logger.getLogger(TransactionMenu.class);

    public static File getActionXML() throws FileNotFoundException {
        File xml;

        System.out.println("Input path to XML");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next().replaceAll("['\"]", "");

        logger.info(String.format("Loading transactions from %s%n", path));

        xml = new File(path);

        if (xml.exists()) {
            return xml;
        } else {
            throw new FileNotFoundException("Can`t find xml in " + path);
        }
    }

    public static void main(String[] args) {
        TransactionPool pool = new TransactionPool();

        PropertyConfigurator.configure(Log4jConfig.configProperties());

        try {
            pool.runActionXML(getActionXML());
            System.out.println("Transactions done");

            if (pool.isError()) {
                System.out.println("During operation, there were errors, see details in the " + Log4jConfig.getLogFile().getAbsolutePath());
            }

        } catch (SAXException | FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }
}
