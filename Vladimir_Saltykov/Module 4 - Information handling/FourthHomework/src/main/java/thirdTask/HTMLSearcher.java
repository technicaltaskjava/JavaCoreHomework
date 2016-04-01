package thirdTask;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLSearcher {

    private static final String FILE = "4. Information handling_task_attachment.html";

    public static void main(String[] args) throws FileNotFoundException {

        String text = readFile(FILE);
        System.out.println(search(text));

    }



    public static String readFile(String file) {
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

    public static String search(String text) {
        Pattern refPattern = Pattern.compile("[Р|р](ис[.]|исунке)[ ]?([0-9]{1,2})");
        StringBuilder stringBuilder = new StringBuilder();

        Pattern pattern = Pattern.compile("[\\!|\\.\\?]+\\s?");

        String[] myStr = pattern.split(text);
        for (String t : myStr) {
            Matcher match = refPattern.matcher(t);
            if (match.find()) {

                stringBuilder.append(t + " ");
            }
        }

        return stringBuilder.toString();
    }
}
