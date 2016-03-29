package tests.task1;

import java.io.FileNotFoundException;
import task1.Assert;
import task1.Test;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class TaskOneTest {

    @Test
    public void testPass(){
     String str = "My Junit";
     String expected = "My Junit";
        Assert.assertEquals("Objects do not equals", str, expected);
    }
    @Test
    public void testFail(){
        String str = "My Junit";
        String expected = "Junit";
        Assert.assertNotEquals("Objects do not equals", str, expected);
    }

    @Test(ignore = true)
    public void testNeverRun() throws Throwable {
      throw new Throwable();
    }

    @Test(expected = FileNotFoundException.class)
    public void testExceptionPass() throws FileNotFoundException {
       throw new FileNotFoundException();
    }
    @Test(expected = FileNotFoundException.class)
    public void testExceptionFail() throws FileNotFoundException {
        throw new ArithmeticException();
    }
}
