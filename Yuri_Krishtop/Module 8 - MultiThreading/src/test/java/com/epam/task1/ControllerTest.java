package com.epam.task1;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yuriy Krishtop on 17.04.2016.
 */
public class ControllerTest {

    @Test
    public void isDataValidTest() {
        int[] inputTrue = {1, 100, 4};
        assertTrue(ControllerFindPrime.isInputDataValid(inputTrue));
        int[] inputFalse = {-1, 100, 3};
        assertFalse(ControllerFindPrime.isInputDataValid(inputFalse));
    }
}
