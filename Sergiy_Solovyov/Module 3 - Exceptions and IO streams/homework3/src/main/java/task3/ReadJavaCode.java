package task3;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 11.03.2016
 */
public class ReadJavaCode {

    public Map<String, Integer> readFile(String fileName){


        Map<String, Integer> theKeywordCount = new HashMap<String, Integer>();
        for (String str : JavaKeywords.keywords) {
            theKeywordCount.put(str, 0);
        }


        File file = new File("javacode.txt");
        if (!file.exists()){
            System.out.println("File not found");
            return null;
        }

        ArrayList<String> arrayList = new ArrayList<String>();

        try (BufferedInputStream  inFile = new BufferedInputStream(new FileInputStream(file))){

            String words;
            Pattern pattern = Pattern.compile("\\w+");
            StringBuilder builder = new StringBuilder();
            int ch;
            while((ch = inFile.read()) != -1){
                builder.append((char)ch);
            }

            words = builder.toString();
            Matcher matcher = pattern.matcher(words);
            while (matcher.find()) {
                arrayList.add(matcher.group());
            }
            System.out.println("Reading file: " + file.getAbsolutePath());

        } catch (Exception e) {
         e.printStackTrace();
        }
        System.out.println("File: " + file.getName() + " has been read");
        for (Map.Entry<String, Integer> entry : theKeywordCount.entrySet())
        {
            int value = entry.getValue();
            for (String word: arrayList){
                if (entry.getKey().equals(word)){
                    entry.setValue(entry.getValue() + 1);}
            }
        }

       return theKeywordCount;
    }

    public  void createAndWriteFile(String stringMap, String newFileName){

        File keywords = new File(newFileName);
        try {
            if (keywords.createNewFile())

                System.out.println("File "+keywords.getName() + " created");
            else
                System.out.println("File "+keywords.getName() + " already exists");

        } catch (IOException e) {

            System.out.println("Error during creation file: " + keywords.getAbsolutePath());
        }

        try (BufferedOutputStream outFile = new BufferedOutputStream(new FileOutputStream(keywords))){

            byte[] contentInBytes = stringMap.getBytes();
            outFile.write(contentInBytes);

        } catch (IOException ex) {

            System.out.println("Error during writing to file: " + keywords.getAbsolutePath());
    }
    }

    public  String mapToString(Map<String, Integer> map){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {   int value = entry.getValue();
            if (value != 0){
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(value);
                sb.append("\n");}
        }

        return sb.toString();
    }
  }

