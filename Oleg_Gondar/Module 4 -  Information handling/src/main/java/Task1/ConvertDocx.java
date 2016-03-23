package Task1;

import org.apache.poi.POITextExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * Created by Oleg on 20.03.2016.
 */
public class ConvertDocx {

    public static String getTextFromDocxFile() {

        POITextExtractor extractor = null;


        try {
            InputStream fis = new FileInputStream("test.docx");

            XWPFDocument doc = new XWPFDocument(fis);
            extractor = new XWPFWordExtractor(doc);
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SentenceUtilites.replaceWhithSpaces(extractor.getText());

    }
}
