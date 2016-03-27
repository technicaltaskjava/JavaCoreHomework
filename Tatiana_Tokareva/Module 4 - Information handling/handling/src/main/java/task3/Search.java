package task3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Search {
    private Pattern refPattern = Pattern.compile("[Р|р](ис[.]|исунке)[ ]?([0-9]{1,2})");

    public String readFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream, "cp1251"));
            String str;
            while ((str = in.readLine()) != null) {


                    stringBuilder.append(str+"\n");

            }
            in.close();
        } catch (IOException e) {
        }
        return stringBuilder.toString();
    }


   /*  StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream, "cp1251"));
            String str;
            while ((str = in.readLine()) != null) {
                Matcher matcher = refPattern.matcher(str);

                if (matcher.find()) {

                stringBuilder.append(str+"\n");
                }
            }
            in.close();
        } catch (IOException e) {
        }
        return stringBuilder.toString();
    }*/

    public String vuv(String text) {
        StringBuilder stringBuilder = new StringBuilder();
       // Pattern pattern = Pattern.compile("[\!|\.|\?]+\s?");
        Pattern pattern = Pattern.compile("[\\!|\\.\\?]+\\s?");
       String[] myStr=pattern.split(text);
        for (String t:myStr) {
            Matcher m = refPattern.matcher(t);
            if (m.find()) {

                stringBuilder.append(t+" ");
           }
        }


        return stringBuilder.toString();
    }

}
