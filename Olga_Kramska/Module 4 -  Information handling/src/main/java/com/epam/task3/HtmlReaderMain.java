package com.epam.task3;

/**
 * Created by Olga Kramska on 16-Mar-16.
 */
public class HtmlReaderMain {
    public static void main(String[] args) {
        String fileName = "4. Information handling_task_attachment.html";
        HTMLReader reader = new HTMLReader(fileName, "CP1251");
        reader.printSentenceWithImageLink();
    }
}
