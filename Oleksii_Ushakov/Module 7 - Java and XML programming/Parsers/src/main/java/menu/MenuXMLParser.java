package menu;

import org.xml.sax.SAXException;
import xml.actor.Actor;
import xml.dom.DOMActorParser;
import xml.sax.SAXActorParser;
import xml.stax.StAXActorParser;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Alexey Ushakov
 */
public class MenuXMLParser {
    private static PrintStream output = System.out;//NOSONAR

    private MenuXMLParser() {
    }

    public static void drawRepetitiveSymbol(char symbol, int times) {
        for (int i = 0; i < times; i++) {
            output.print(symbol);
        }
        output.println();
    }

    public static void viewActorSet(Set<Actor> actors) {
        if (actors.isEmpty()) {
            output.println("Nothing to show");
        } else {
            drawRepetitiveSymbol('-', 72);
            output.printf("|%-15s     ", "Actor name");
            output.printf("|%14s", "Replicas count");
            output.printf("|%13s ", "Words count");
            output.printf("|%19s|%n", "Words average count");
            drawRepetitiveSymbol('-', 72);
            for (Actor actor : actors) {
                output.printf("|  %-18s", actor.getName());
                output.printf("|%12s  ", actor.getReplicasCount());
                output.printf("|%12s  ", actor.getWordsCount());
                output.printf("|%17s  |%n", actor.getWordsAverageCount());
            }
            drawRepetitiveSymbol('-', 72);
        }

    }

    public static String getPathToXML() {
        Scanner scanner = new Scanner(System.in);
        output.print("Input path to XML: ");
        return scanner.next();
    }

    public static void main(String[] args) throws SAXException {
        try {
            File xmlFile = new File(getPathToXML().replaceAll("'", ""));


            Set<Actor> actors = SAXActorParser.parseActors(xmlFile);
            output.println("\nSAX says");
            viewActorSet(actors);

            output.println("\nStAX says");
            actors = StAXActorParser.parseActors(xmlFile);
            viewActorSet(actors);

            output.println("\nDOM says");
            actors = DOMActorParser.parseActors(xmlFile);
            viewActorSet(actors);

        } catch (SAXException | XMLStreamException e) {
            throw new SAXException(e.getMessage(), e);
        }
    }
}
