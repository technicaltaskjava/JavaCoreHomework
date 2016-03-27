package task1;

import messages.Message;
import console.WrongCharException;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 18.03.2016
 */
public class WordRemover {

    private StringBuilder sb = new StringBuilder();
    private Message m = new Message();
    private Pattern pattern = Pattern.compile("(\\b)((?![\\d])\\w)+(\\b)", Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern pattern2 = Pattern.compile("(( )+|(\\t)+)+", Pattern.UNICODE_CHARACTER_CLASS);

    public String readFile(String fileName, char firstLetter, int wordLength) throws WrongCharException, FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            m.warn("File not found");
            throw new FileNotFoundException();
        }
        if (!isConsonant(firstLetter)) {
            throw new WrongCharException("Wrong char");
        }

        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));) {

                String currentLine;
                ArrayList<String> wordsToDel = new ArrayList<>();
                while ((currentLine = br.readLine()) != null) {
                    sb.append(currentLine);
                    sb.append("\n");
                }
                Matcher matcher = pattern.matcher(sb);
                while (matcher.find()) {
                    String word = matcher.group();
                    if (isNeededWord(word, firstLetter, wordLength)){
                        wordsToDel.add(word);
                    };
                }
                for (String str: wordsToDel){
                    replaceString(str, "");
                }
                replaceString("  "," ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    private static boolean isNeededWord(String word, char firstLetter, int wordLength ){
        if (word.length() != wordLength)return false;
        if (firstLetter == word.charAt(0)){
            return true;}

        return false;
    }
    private static boolean isConsonant(char firstLetter){
        for (Character c : Letters.CONSONANTS){
            if (c == firstLetter) return true;
        }
        return false;
    }
    private  void replaceString(String toReplace,
                                      String replacement) {
        int index = -1;
        while ((index = sb.lastIndexOf(toReplace)) != -1) {
            sb.replace(index, index + toReplace.length(), replacement);
        }
        }
    }



