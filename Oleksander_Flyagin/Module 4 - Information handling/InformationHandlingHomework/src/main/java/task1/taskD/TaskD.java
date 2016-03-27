package task1.taskD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskD
    {
        String text;

        public TaskD(String text)
            {
                this.text = text;
            }

        public void getTextRevers()
            {
                System.out.println(splitText());
            }

        String splitText()
        {
            String resoult = "";
            String [] words =  text.split(" ");
            for (String word:words)
                {
                    resoult +=reverChars(word) +" ";
                }
            return resoult;
        }

        String reverChars(String text)
            {
                String res = "";
                char[]  charSatrt =  text.toCharArray();
                String start = String.valueOf(charSatrt[0]);
                StringBuffer buffer = new StringBuffer();
               Pattern regexp = Pattern.compile("["+start+start.toLowerCase()+"]");
                Matcher m = regexp.matcher(text);
                while (m.find())
                    {
                        m.appendReplacement(buffer, "");
                    }
                m.appendTail(buffer);
                res = buffer.toString();
                return res;
            }




    }
