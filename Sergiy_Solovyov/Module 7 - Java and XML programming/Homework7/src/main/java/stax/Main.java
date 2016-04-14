package stax;

import javax.xml.stream.XMLStreamException;

/**
 *  @author Sergey Solovyov
 */
public class Main {

private Main(){}

    public static void main(String[] args) throws XMLStreamException {
        StAXParser stAX = new StAXParser();
        stAX.parse("src/main/resources/a_and_c.xml");
        System.out.println(stAX.toString());;
    }
}
