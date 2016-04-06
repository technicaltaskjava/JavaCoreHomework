package javase.t03.demo;

import javase.t03.numbers.Numbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for number list demo
 * Created by Yury Vislobodsky on 01.04.2016.
 */
public class NumbersDemo {
    private static Logger logger = LoggerFactory.getLogger(NumbersDemo.class);

    NumbersDemo() {}

    public static void main(String[] args) {
        Numbers<Integer> elemInteger = new Numbers<>();
        elemInteger.addElement(5);
        elemInteger.addElement(1);
        elemInteger.addElement(2);
        elemInteger.addElement(9);
        elemInteger.addElement(4);
        elemInteger.addElement(5);
        elemInteger.addElement(2);
        elemInteger.addElement(6);
        logger.info("Integer numbers : " + elemInteger);
        elemInteger.deleteElement(2);
        elemInteger.deleteElementByIndex(1);
        elemInteger.addElementByIndex(0, 4);
        logger.info("After changes : " + elemInteger);
        logger.info("Length : " + elemInteger.getLength());
        logger.info("Searching element 8, found element : " + elemInteger.searchElement(8));
        logger.info("Searching element 7, found element : " + elemInteger.searchElement(7));
        logger.info("Searching element 5, found element : " + elemInteger.searchElement(5));
    }
}
