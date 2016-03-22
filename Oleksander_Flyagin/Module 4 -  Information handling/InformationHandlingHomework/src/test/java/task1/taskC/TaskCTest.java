package task1.taskC;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskCTest
    {
        TaskC taskC = new TaskC();

        @Test
        public void testSplitOnWords() throws Exception
            {
                String text = " Вам надо проверить удаление, внесите пожалуйста размер слова аааб  ооо акакак обл оео";
                String newText =  taskC.splitOnWords(text, 3);
                String exp = "  надо проверить удаление, внесите пожалуйста размер слова аааб  ооо акакак обл оео";
                Assert.assertEquals(newText,exp);



            }
    }