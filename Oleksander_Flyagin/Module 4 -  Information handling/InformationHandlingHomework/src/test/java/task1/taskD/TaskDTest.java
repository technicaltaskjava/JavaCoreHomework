package task1.taskD;

import org.junit.Assert;
import org.junit.Test;
import task1.taskC.TaskC;

import static org.junit.Assert.*;

public class TaskDTest
    {

        @Test
        public void testReverChars() throws Exception
            {
                TaskD taskD = new TaskD("Моя короква ОбоШла");
                String resoult = taskD.splitText();
                String exp = "оя орова бШла ";
                Assert.assertEquals(resoult, exp);

            }
    }