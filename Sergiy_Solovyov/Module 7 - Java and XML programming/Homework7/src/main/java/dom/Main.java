package dom;

import exceptions.MyDOMException;


/**
 *  @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) throws MyDOMException {

        DOMPlayParser playParser = new DOMPlayParser();
        playParser.parse("src/main/resources/a_and_c.xml");
        System.out.println(playParser.toString());

    }
}
