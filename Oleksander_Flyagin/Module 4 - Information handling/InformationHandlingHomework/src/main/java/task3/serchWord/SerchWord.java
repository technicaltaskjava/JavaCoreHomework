package task3.serchWord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SerchWord
    {
        

      public void   grepLine(String text)
            {
                String searchText = "";
                Pattern regexp = Pattern.compile("\\b[А-Я]\\W+((\\(Рис.\\s\\d+\\s\\W+\\d+\\))|" +
                                                                                       "(\\(Рис.\\s\\d+\\)))\\W+([.])");
                Matcher m = regexp.matcher(text);
                while (m.find())
                    {
                        searchText+=m.group()+"\r\n";
                    }
                System.out.println(searchText);

            }


    }
