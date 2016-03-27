package ua.valentin.htmlparser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Line> refLines = new ArrayList<>();
        List<Line> picLines = new ArrayList<>();
        Parser parser = new Parser();
        parser.parse(refLines, picLines);
        parser.setState(refLines, picLines);
        printLines(refLines);
        printLines(picLines);
    }

    public static void printLines(List<Line> lines) {
        lines.forEach(System.out::println);
    }
 }

