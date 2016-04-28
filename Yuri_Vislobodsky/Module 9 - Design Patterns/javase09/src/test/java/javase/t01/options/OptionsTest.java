package javase.t01.options;

import javase.t01.demo.OptionsDemo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test for Options class with Singleton pattern
 * Checks parameter value when incrementing it
 * Created by Yury on 22.04.2016.
 */
public class OptionsTest {

    @Test
    public void testOptions() {
        int parameter = OptionsDemo.nextValue();
        assertEquals("error", parameter + 1, OptionsDemo.nextValue());
    }
}