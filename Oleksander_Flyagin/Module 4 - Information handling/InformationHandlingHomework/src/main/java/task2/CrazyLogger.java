package task2;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrazyLogger
    {


        public CrazyLogger()
            {
            }

        StringBuilder massageStrore = new StringBuilder();
        SimpleDateFormat clientFormat =  new SimpleDateFormat("yyyy.mm.dd : HH:mm:ss");
        String dataMassege = clientFormat.format(new Date());


        public void write(String storyLog)
            {
                massageStrore.append(dataMassege + " :: " + storyLog+"\r\n");
            }

        public String getMassage()
            {
                return massageStrore.toString();

            }


        public void grep()
            {
                System.out.print("cat \\log\\logCalck | grep ");
                Scanner marker = new Scanner(System.in);
                System.out.println(grepLine(getMassage(),marker.next()));



            }

         String grepLine(String text, String marker)
            {
                String test = "";
                Pattern regexp = Pattern.compile("(([0-9]+[\\.]*)+\\s[\\:]+\\s([0-9]+[\\:])*)+([\\w]+)\\s(("+marker+"|"+marker.toUpperCase()+")\\s)(\\s?[a-zA-Z\\']*)*");
                Matcher m = regexp.matcher(text);
                while (m.find())
                    {
                        test+=m.group();
                    }
                return test;

            }

    }






