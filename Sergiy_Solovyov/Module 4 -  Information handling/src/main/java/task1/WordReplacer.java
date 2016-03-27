package task1;

import messages.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 18.03.2016
 */
public class WordReplacer {
    private StringBuilder sb = new StringBuilder();
    private StringBuilder builder = new StringBuilder();
    private Message m = new Message();
    private Pattern pattern = Pattern.compile("[.?!]( )+[А-Я][а-я ]",
            Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern pattern2 = Pattern.compile("\\w+",
            Pattern.UNICODE_CHARACTER_CLASS);


    public String readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            m.warn("File not found");
            throw new FileNotFoundException();
        }

        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));) {

                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    sb.append(currentLine);
                }
                Matcher matcher = pattern.matcher(sb);
                int beginSentence = 0;
                while (matcher.find()) {
                    sb.setCharAt(matcher.start() + 1, '\n');
                }
                String[] strings = sb.toString().split("\n");
                for (int i = 0; i < strings.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder(strings[i]);
                    Matcher matcher2 = pattern2.matcher(stringBuilder);
                    ArrayList<String> arrayList = new ArrayList<>();
                    while (matcher2.find()) {
                        arrayList.add(matcher2.group());
                    }
                    String firstWord = arrayList.get(0);
                    String lastWord = arrayList.get(arrayList.size() - 1);
                    replaceString(stringBuilder, lastWord, firstWord);
                    stringBuilder.replace(0 , firstWord.length(), lastWord);
                    builder.append(stringBuilder);
                    builder.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  builder.toString();
    }
    private static void replaceString(StringBuilder sb,
                                     String toReplace,
                                     String replacement) {
        int index = -1;
        while ((index = sb.lastIndexOf(toReplace)) != -1) {
            sb.replace(index, index + toReplace.length(), replacement);
        }
    }
}