package com.epam.modul7.xmlParser.dom;

import java.io.File;

public class Main
    {
        private Main()
            {
            }

        public static void main(String[] args)
            {
                DomPars domPars = new DomPars();
                domPars.showPerson(new File("src/main/resources/rich_ii.xml"));
            }
    }
