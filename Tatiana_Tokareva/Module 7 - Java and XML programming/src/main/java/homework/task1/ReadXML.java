package homework.task1;

import homework.Constant;
import homework.task1.parser.Dom;
import homework.task1.parser.Sax;
import homework.task1.parser.Stax;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class ReadXML {
    private ReadXML() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Start DOM parser");
        Map<String, ArrayList<String>> parse = Dom.parse();
        printStat(parse);
        System.out.println("-------------------------");
        System.out.println("Start SAX parser");
        Sax sax = new Sax();
        parse = sax.parse();
        printStat(parse, Constant.WILLIAM);

        System.out.println("-------------------------");
        System.out.println("Start Stax parser");
        Stax stax=new Stax();
        parse = stax.parse();
        printStat(parse, Constant.WILLIAM);
    }

    private static void printStat(Map<String, ArrayList<String>> parse) {
        Set<String> keySet = parse.keySet();
        for (String s : keySet) {
            printStat(parse, s);
        }
    }

    private static void printStat(Map<String, ArrayList<String>> parse, String key) {
        int count = Calc.getCount(key, parse);
        int average = Calc.gatAverage(key, parse);
        System.out.println(key);
        System.out.println(count);
        System.out.println(average);
    }
}


