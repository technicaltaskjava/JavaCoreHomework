package task4;

import messages.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 11.03.2016
 */
public class ReadJavaCode {

    private Message m = new Message();

    public Map<String, Integer> readFile(String fileName){


        Map<String, Integer> theKeywordCount = new HashMap<String, Integer>();
        for (String str : JavaKeywords.keywords) {
            theKeywordCount.put(str, 0);
        }


        File file = new File(fileName);
        if (!file.exists()){
            m.warn("File not found");
            return null;
        }

        ArrayList<String> arrayList = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(file));) {

            String currentLine;
            Pattern pattern = Pattern.compile("\\w+");
            while ((currentLine = br.readLine()) != null) {

                Matcher matcher = pattern.matcher(currentLine);
                while (matcher.find()) {
                    arrayList.add(matcher.group());
                }
            }
            m.info("Reading file: " + file.getAbsolutePath());

        } catch (IOException e) {
         e.printStackTrace();
        }
        m.info("File: " + file.getName() + " has been read");
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

                m.info("File "+keywords.getName() + " created");
            else
                m.info("File "+keywords.getName() + " already exists");

        } catch (IOException e) {

            m.warn("Error during creation file: " + keywords.getAbsolutePath());
        }

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(keywords)));){

            out.write(stringMap);

        } catch (IOException ex) {

            m.warn("Error during writing to file: " + keywords.getAbsolutePath());
    }
    }

    public    String mapToString(Map<String, Integer> map){
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

