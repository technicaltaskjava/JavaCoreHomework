package task2;

import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import static org.junit.Assert.*;

public class CrazyLoggerTest
    {
        CrazyLogger crazyLogger = new CrazyLogger();

        @Test
        public void testGrepLine() throws Exception
            {
                String test=
                        "2016.29.20 : 15:29:31 :: STATUS START OPERATION\n" +
                        "2016.29.20 : 15:29:31 :: STATUS START DIVIDE OPERATION\n" +
                        "2016.29.20 : 15:29:31 :: STATUS ERROR DON'T COMPLIT OPERATION OF DIVIDE\n" +
                        "2016.29.20 : 15:29:31 :: STATUS EXIT";


                String res = crazyLogger.grepLine(test ,"start divide");
                String exp = "2016.29.20 : 15:29:31 :: STATUS START DIVIDE OPERATION\n";

                Assert.assertEquals(res, exp);


            }
    }