package pomwriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


/**
 *  @author Sergey Solovyov
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main(){}

    public static void main(String [] args) {

        try {
            PomWriter pomWriter = new PomWriter();
            pomWriter.writePomXML("myPom.xml");
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            LOGGER.info(pce.getClass().toString(), pce);
        } catch (TransformerException tfe) {
            LOGGER.info(tfe.getClass().toString(), tfe);
        }
    }
}
