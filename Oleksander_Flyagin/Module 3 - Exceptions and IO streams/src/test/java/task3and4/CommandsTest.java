package task3and4;

import org.junit.*;

import static org.junit.Assert.*;

public class CommandsTest
    {

        @org.junit.Test
        public void testLine() throws Exception
            {
                Commands comad = new Commands();
                String words;
                String texts = "int; private; void  public txt test try; case; test try";
                words = comad.line(texts);
                int size = 6;
                String test = "int \nprivate \nvoid \npublic \ntry \ncase\nWords are : " + size;

                Assert.assertEquals(words, test);



            }
    }