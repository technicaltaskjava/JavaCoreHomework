package com.epam.javase04.task03_htmlParser;

import com.epam.javase04.task03_htmlParser.reader.HtmlReader;
import com.epam.javase04.task03_htmlParser.splitter.Splitter;
import com.epam.javase04.task03_htmlParser.writer.HtmlWriter;

public class MainHtml {

    public static void main(String[] args) {
        HtmlReader reader = new HtmlReader();
        StringBuilder allText = reader.readFile("4. Information handling_task_attachment.html");

        Splitter splitter = new Splitter();
        splitter.splitToSentence(allText);

        HtmlWriter writer = new HtmlWriter();
        writer.writeToFile(allText, "OutputHtmlText.txt");
    }
}
