package task1;

import messages.Message;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 18.03.2016
 */
public class LetterRemover {
    private HashMap<String, String> wordsToEdit = new HashMap<String, String>();
    private StringBuilder sb = new StringBuilder(512);
    private Message m = new Message();
    private Pattern pattern = Pattern.compile("(\\b)((?![\\d])\\w)+(\\b)", Pattern.UNICODE_CHARACTER_CLASS);

    public String readAndEditFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            m.warn("File not found");
            throw new FileNotFoundException();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));) {

                String currentLine;

                while ((currentLine = br.readLine()) != null) {
                    sb.append(currentLine);
                    sb.append("\n");
                }
            Matcher matcher = pattern.matcher(sb);
            while (matcher.find()) {
                deleteLetters(matcher.group());

            }
            for (Map.Entry<String, String> entry : wordsToEdit.entrySet())
            {
                replaceString(entry.getKey(), entry.getValue());
            }

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
            e.printStackTrace();
            }

        return sb.toString();
    }
    /*
    * method recovers words with capital letters in the middle of the word
    * for example "BufferString" but another is faster: 170 ms vs 360ms
    * */
    public boolean deleteLetters(String word){             //method recover words with capital letters
        char []chars = word.toCharArray();                 // for example "BufferString" but another is faster
        char [] chars1 = word.toLowerCase().toCharArray();
        char []finalArr = null;
        char firstChar = word.charAt(0);
        int zero = 0;
        for (int i = 1; i < chars1.length; i++){
            if (chars1[i] == chars1[0]){
                chars1[i] = 0;
                zero++;
            }
        }
        if (zero == 0) return false;
        else {
        for (int i = 0; i < chars1.length; i++){
            if (chars1[i] != 0)chars1[i] = chars[i];
        }
            finalArr = new char[chars.length-zero];
            int count = 0;
            for (int i = 0; i < chars1.length; i++){
                if (chars1[i] == 0)continue;
                    finalArr[count] = chars1[i];
                    count++;
            }
        }
        wordsToEdit.put(word, String.valueOf(finalArr));

       return true;
    }
    private  void replaceString(String toReplace, String replacement) {

        int index = -1;
        while ((index = sb.lastIndexOf(toReplace)) != -1) {
            sb.replace(index, index + toReplace.length(), replacement);
        }
        }
//    public static String deleteLettersFast(String word){
//        char firstChar = word.charAt(0);
//        StringBuilder builder = new StringBuilder(word.toLowerCase());
//        int i;
//        while ((i = builder.indexOf(Character.toString(builder.charAt(0)), 1)) != -1){
//
//            builder.deleteCharAt(i);
//        }
//        builder.setCharAt(0, firstChar);
//        return builder.toString();
//    }

}
