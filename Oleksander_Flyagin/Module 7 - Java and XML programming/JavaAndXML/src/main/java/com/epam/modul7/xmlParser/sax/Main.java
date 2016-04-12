package com.epam.modul7.xmlParser.sax;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main
    {
        private Main()
            {

            }
        public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
            {
                SAXParserFactory parserF = SAXParserFactory.newInstance();
                SAXParser saxParser = parserF.newSAXParser();
                SaxPars handler = new SaxPars();
                saxParser.parse(new File("src/main/resources/rich_ii.xml"), handler);
                handler.showElement();


            }
    }
