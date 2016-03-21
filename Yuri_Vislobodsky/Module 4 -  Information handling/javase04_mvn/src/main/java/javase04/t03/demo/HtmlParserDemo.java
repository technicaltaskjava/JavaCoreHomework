package javase04.t03.demo;

import javase04.common.library.ScannerUtils;
import javase04.t03.parser.HtmlParser;
import java.io.IOException;
import java.util.Scanner;

/**
 * HTML Parser demo class
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class HtmlParserDemo {
    private HtmlParser htmlParser;
    private Scanner in;

    public HtmlParserDemo() {
        htmlParser = new HtmlParser();
        in = new Scanner(System.in);
    }

    public void doLoadFromFile() {
        if (!htmlParser.loadFromFile("task03.html")) {
            htmlParser.loadFromFile(ScannerUtils.tryToInputString(in, "Load from file : "));
        }
    }

    public void doResultToScreen() {
        try {
            htmlParser.resultToStream(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HtmlParserDemo demo = new HtmlParserDemo();
        demo.doLoadFromFile();
        demo.doResultToScreen();
    }
}
