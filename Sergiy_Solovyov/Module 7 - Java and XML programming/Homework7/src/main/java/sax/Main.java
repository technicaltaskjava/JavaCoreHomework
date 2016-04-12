package sax;

import exceptions.MySAXException;
import sax.saxparser.SAXParser;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) throws MySAXException {
        SAXParser parser = new SAXParser();
        parser.parse("src/main/resources/a_and_c.xml");
    }
}
