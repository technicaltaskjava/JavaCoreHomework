package javase.t01.demo;

import javase.t01.dom.PlayParserDom;
import javase.t01.exception.PlayParserException;
import javase.t01.play.Play;
import javase.t01.sax.PlayParserSax;
import javase.t01.stax.PlayParserStax;

/**
 * Play Parsers Demo Class
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class PlayParsersDemo {
    private PlayParsersDemo() { }

    public static void main(String[] args) throws PlayParserException {
        String demoFileName = "src/main/resources/hamlet.xml";
        Play play = PlayParserDom.parsing(demoFileName);
        play.printStatistics("DOM Parser results:");

        play = PlayParserSax.parsing(demoFileName);
        play.printStatistics("SAX Parser results:");

        play = PlayParserStax.parsing(demoFileName);
        play.printStatistics("StAX Parser results:");
    }
}
