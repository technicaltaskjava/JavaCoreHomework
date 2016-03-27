package task3.serchWord;

import org.junit.Test;

import static org.junit.Assert.*;

public class SerchWordTest
    {

        @Test
        public void testGrepLine() throws Exception
            {
                SerchWord serchWord = new SerchWord();
                String text = "Всего в спиновой трубке протона находятся 37 электронов и позитронов (18 электронов и 19 позитронов)," +
                        " следовательно спиновой заряд протона в 37 раз превосходит" +
                        " спиновой заряд электрона. На рисунке (Рис. 7) показан принцип нейтрализации спинового, неэлектростатического " +
                        "заряда протона и, спинового неэлектростатического заряда электрона в структуре атома гелия.";
                        serchWord.grepLine(text);

            }
    }