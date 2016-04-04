package com.epam.t01;

import java.lang.reflect.Method;

public class ReflectionTestMain {
    public static void mainMenu() {
        int testPass = 0;
        int testFail = 0;
        int testError = 0;
        int testIgnore = 0;
        int methodCount = 0;

        for(Method methodIndex : TestClass.class.getMethods()) {
            methodCount++;
            if(methodIndex.isAnnotationPresent(Test.class)) {
                Test currentAnnotation = methodIndex.getAnnotation(Test.class);
                try {
                    System.out.print("METHOD " + methodIndex.getName() + "() : ");
                    if(currentAnnotation.ignore() == true) {
                        testIgnore++;
                        System.out.println("Test ignored.");
                    }
                    else {
                        methodIndex.invoke(new TestClass());
                        testPass++;
                        System.out.println("Test passed. Assertion is true.");
                    }
                }
                catch (Throwable e) {
                    if(currentAnnotation.expected().getName().equals(e.getCause().getClass().getName())) {
                        testFail++;
                        System.out.println("Test failed. " + currentAnnotation.expected().getName() + " catched.");
                    }
                    else {
                        testError++;
                        System.out.println("Test Error. Ecxecption :" + e.getCause().getClass().getName());
                    }
                }
            }
        }
        System.out.println();
        System.out.print("Pass: " + testPass + "; ");
        System.out.print("Fail: " + testFail + "; ");
        System.out.print("Error: " + testError + "; ");
        System.out.println("Ignore: " + testIgnore);
        /* nine methods are inherited from Object for any class*/
        System.out.println("Methods: " + (methodCount-9) + "; Tested: " + (testPass + testFail + testError + testIgnore));
        if (testError == 0) {
            System.out.println("Test is completed.");
        }
        else {
            System.out.println("The test is failed. Errors present.");
        }
    }
}