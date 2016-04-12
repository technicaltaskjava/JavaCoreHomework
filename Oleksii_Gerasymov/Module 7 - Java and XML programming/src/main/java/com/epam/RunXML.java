package com.epam;

import com.epam.first.PlayList;
import com.epam.first.dom.ParsingByDom;
import com.epam.first.sax.ParsingBySax;
import com.epam.first.stax.ParsingByStax;
import com.epam.second.XmlOut;

public class RunXML {
    public static final String PLAY = "src/main/resources/com_err.xml";

    private RunXML() {
    }

    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        PlayList speakerList = ParsingByDom.parseRun(PLAY);
        long timeSpend = System.currentTimeMillis() - timeStart;
        System.out.println("Running time by DOM is " + timeSpend + " ms.");
        System.out.println(speakerList.outPlayListData());

        timeStart = System.currentTimeMillis();
        speakerList = ParsingBySax.parseRun(PLAY);
        timeSpend = System.currentTimeMillis() - timeStart;
        System.out.println("Running time by SAX is " + timeSpend + " ms.");
        System.out.println(speakerList.outPlayListData());

        timeStart = System.currentTimeMillis();
        speakerList = ParsingByStax.parseRun(PLAY);
        timeSpend = System.currentTimeMillis() - timeStart;
        System.out.println("Running time by STAX is " + timeSpend + " ms.");
        System.out.println(speakerList.outPlayListData());

        XmlOut.runXmlOut();

    }
}
