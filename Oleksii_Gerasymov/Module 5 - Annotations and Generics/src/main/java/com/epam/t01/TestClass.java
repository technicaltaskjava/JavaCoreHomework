package com.epam.t01;

import org.junit.ComparisonFailure;
import static org.junit.Assert.assertEquals;

public class TestClass {
        @Test(expected = NumberFormatException.class)
        public void methodWithException() {
            int testInt = Integer.parseInt("string");
        }

        @Test(expected = ComparisonFailure.class)
        public void methodWithoutException() {
            String testString = "test string";
            assertEquals(testString, "test string");
        }

        public void methodWithoutAnnotation() {
            String testString = "This method will not run because there is no annotation.";
        }

        @Test(expected = ComparisonFailure.class)
        public void methodWithAssertException() {
            String testString = "This method will run and throw assertion error...";
            assertEquals(testString, "test string");
        }

        @Test(ignore = true)
        public void ignoredMethodWithException() {
            String testString = "This method will not run in case of ignored status...";
            int testInt = Integer.parseInt("1");
        }
}
