package task1.taskB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB
    {

        public TaskB()
            {
            }

        public void shiftMathEnter(String text)
            {
                for (String words : sortWords(text))
                    {
                        if (words.length()>2)
                            System.out.println(words);
                    }
            }

        String[] sortWords(String text)
            {
                String[] words = splitOnWords(text);
                for(int stap = words.length -1; stap > 0 ; stap--)
                    {
                        for(int byStap = 0; byStap < stap ; byStap++)
                            {
                                if( (Double.compare(attitudeVowels(words[byStap]), attitudeVowels(words[byStap+1])) == 1 ))
                                    {
                                        String tmp = words[byStap];
                                        words[byStap] = words[byStap+1];
                                        words[byStap+1] = tmp;
                                    }
                            }
                    }

                return words;
            }

        String[] splitOnWords(String texts)
            {
                String[] newText = splitDubleWords(texts).split(" ");
                return newText;

            }

        String dellPunctuation(String text)
            {
                StringBuffer buffer = new StringBuffer();
                Pattern regexp = Pattern.compile("(\\.)|(\\,)|(\\d)|(-)|(\\s(\\D)?\\s)");
                Matcher m = regexp.matcher(text);
                while (m.find())
                    {
                        m.appendReplacement(buffer, " ");
                    }
                m.appendTail(buffer);
                return buffer.toString();

            }

        String splitDubleWords(String textNottreatment)
            {
                String text = dellPunctuation(textNottreatment);

                String[] array = new String[0];
                int start = 0;
                int end = 0;
                while (true)
                    {
                        end = text.indexOf(" ", start);
                        if (end == -1) break;
                        String s = text.substring(start, end);
                        start = end + 1;
                        if (!isPresent(array, s))
                            {
                                array = newStringArray(array, s);
                            }

                    }
                String rezult = "";
                for (String s : array)
                    {
                        rezult += s + " ";
                    }

                return rezult.trim();
            }

        String[] newStringArray(String[] array, String str)
            {
                String[] newArray = new String[array.length + 1];
                for (int i = 0; i < array.length; i++)
                    {
                        newArray[i] = array[i];
                    }
                newArray[newArray.length - 1] = str;
                return newArray;
            }

        boolean isPresent(String[] array, String str)
            {
                for (String s : array)
                    {
                        if (s.equals(str))
                            {
                                return true;
                            }
                    }
                return false;
            }


        int sumVowels(String words)
            {
                int res = 0;

                Pattern regexp = Pattern.compile("([АаОоИиЕеЁёЭэЫыУуЮюЯя])");
                Matcher m = regexp.matcher(words);
                while (m.find())
                    {
                        res++;

                    }
                return res;
            }

        double attitudeVowels(String words)
            {

                double res;
                int sum = sumVowels(words);
                int lengthWords = words.length();

                if(sum!= 0)
                    {
                        res = (double)lengthWords / sum;
                    }
                else
                    res =0;
                return res;
            }

    }



