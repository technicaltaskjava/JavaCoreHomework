package com.epam.modul7.xmlParser.stax;


public class Main
    {
        private Main()
            {
            }

        public static void main(String[] args)
            {
                StAXParserDemo parser = new StAXParserDemo();
                parser.shoPersons("src/main/resources/rich_ii.xml");
            }
    }
