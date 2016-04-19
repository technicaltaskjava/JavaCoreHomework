package com.task2;

import com.task2.application.Application;
import com.task2.parser.TextParser;

/**
 * @author Sergey Solovyov.
 */
public class Main {

    private Main() {}

    public static void main(String[] args) throws InterruptedException {
        TextParser parser = new TextParser();
        Application processor = new Application(parser);
        processor.run();
    }
}
