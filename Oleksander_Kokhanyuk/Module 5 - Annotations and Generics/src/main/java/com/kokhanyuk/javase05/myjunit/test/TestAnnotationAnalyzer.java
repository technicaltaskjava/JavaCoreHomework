package com.kokhanyuk.javase05.myjunit.test;

import java.lang.reflect.Method;

/**
 * TestAnnotationAnalyzer
 *
 *This class contains methods for starting methods contain annotations.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class TestAnnotationAnalyzer {
    public void analyz(Class<?> testClass) throws Exception {

        Method[] methods = testClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                boolean ignore = test.ignore();
                Class expected = test.expected();
                try {
                    if (!ignore) {
                        method.invoke(testClass.newInstance());
                        System.out.println("Method " + method.getName() + " is passed Test.\n");
                    } else {
                        System.out.println("Method " + method.getName() + " is ignored Test.\n");
                    }
                } catch (Exception e) {
                    if (e.getCause().getClass() == expected) {
                        System.out.println("Test " + method.getName() + " for an expected Exception is passed.\n");
                    } else {
                        System.out.println("Error " + method.getName() + " unknown Exception.\n" + e.getCause() + " " + e.getMessage());
                    }
                }
            }
        }
    }
}
