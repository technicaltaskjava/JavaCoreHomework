package task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shifts
    {
        String text;

        public Shifts(String text)
            {
                this.text = text;
            }

        public StringBuffer shiftMath()
            {
                StringBuffer buffer = new StringBuffer();
                Pattern regexp = Pattern.compile("((\\s)+)|(\t)+");
                Matcher m = regexp.matcher(text);
                while (m.find())
                    {
                        m.appendReplacement(buffer, " ");
                    }
                m.appendTail(buffer);

                return buffer;
            }
        public String shiftMathToString()
            {
                String res = "";
                StringBuffer buffer = new StringBuffer();
                Pattern regexp = Pattern.compile("((\\s)+)|(\t)+");
                Matcher m = regexp.matcher(text);
                while (m.find())
                    {
                        m.appendReplacement(buffer, " ");
                    }
                m.appendTail(buffer);

                res = buffer.toString();
                return res;
            }


        public StringBuffer shiftMathEnter(StringBuffer text)
            {

                StringBuffer buffer = new StringBuffer();
                Pattern regexp = Pattern.compile("(\\.\\s)");
                Matcher m = regexp.matcher(text.toString());
                while (m.find())
                    {
                        m.appendReplacement(buffer, ".\r\n");
                    }
                m.appendTail(buffer);

                return buffer;
            }
    }
