package task1.taskC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskC
    {
        String texts;
        int size;

        public TaskC( String texts, int size)
            {
                this.size = size;
                this.texts = texts;
            }

        public TaskC()
            {
            }

        String splitOnWords(String texts, int size)
            {
                    int length = size-1;
                    String res = "";
                    StringBuffer buffer = new StringBuffer();
                    Pattern regexp = Pattern.compile("\\b([БбВвГгДдЖжЗзЙйКкЛлМмНнПпРрСсТтФфХхЦцЧчШшЩщ])\\D{" +length  +
                                                                                                 "," +length + "}\\b");
                    Matcher m = regexp.matcher(texts);
                    while (m.find())
                        {
                            m.appendReplacement(buffer, "");
                        }
                    m.appendTail(buffer);

                    res = buffer.toString();
                    return res;
                }


        public void getSerchWords()
            {
                System.out.println(splitOnWords(texts,size));
            }


    }
