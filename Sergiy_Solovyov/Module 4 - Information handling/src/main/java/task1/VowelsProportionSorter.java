package task1;

import messages.Message;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public class VowelsProportionSorter implements Comparator<String> {

    private   List<String> words = new ArrayList<String>();
    private Message m = new Message();

    public String readFile(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        if (!file.exists()){
            m.warn("File not found");
            throw new FileNotFoundException();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));) {

            String currentLine;
            Pattern pattern = Pattern.compile("(\\b)((?![\\d])\\w)+(\\b)", Pattern.UNICODE_CHARACTER_CLASS);  //pattern to find words that consist of only letters
            while ((currentLine = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(currentLine);
                while (matcher.find()) {
                    words.add(matcher.group());
                }
            }

            Collections.sort(words, new VowelsProportionSorter());

            m.info("Reading file: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        m.info("File: " + file.getName() + " has been read");
        Formatter formatter = new Formatter();
        for (String str: words){
            formatter.format("%s  -  %.2f %%\n" ,str, getPercent(str));

    }
        return formatter.toString();
    }
    public static double getPercent(String word){
        double l1 = Double.valueOf(word.length());
        char[] chars = word.toCharArray();
        int vow = 0;
        for (Character p: chars){
            for (int i = 0; i < Letters.VOWELS.length; i++){
                if (Letters.VOWELS[i] == p){
                    vow++;}
            }
        }
        return (vow/l1)*100;
    }


    @Override
    public int compare(String o1, String o2) {
        int vow1 = 0;
        int vow2 = 0;
        double l1 = o1.length();
        double l2 = o2.length();
        char[] chars1 = o1.toCharArray();
        char[] chars2 = o2.toCharArray();
        for (Character p: chars1){
            for (int i = 0; i < Letters.VOWELS.length; i++){
                if (Letters.VOWELS[i] == p){
                    vow1++;}
            }
        }
        for (Character p: chars2){
            for (int i = 0; i < Letters.VOWELS.length; i++){
                if (Letters.VOWELS[i] == p){
                    vow2++;}
            }
        }
        return (int)((vow1/l1)*10000) - (int)((vow2/l2)*10000);
    }
}
