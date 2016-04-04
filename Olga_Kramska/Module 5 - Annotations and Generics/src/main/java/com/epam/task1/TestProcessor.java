package com.epam.task1;

import java.lang.reflect.Method;

/**
 * Created by Olga Kramska on 27-Mar-16.
 */
public class TestProcessor {

    private TestProcessor(){}

    public static void process(Object object) {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            Test test = method.getAnnotation(Test.class);
            if (test != null && !test.ignore()) {
                try {
                    method.invoke(object);
                } catch (ReflectiveOperationException e) {
                    Class<? extends Throwable> expectedClassExc = test.expected();
                    if (e.getCause().getClass() != expectedClassExc) {
                        throw new RuntimeException("Test failed: " + method.getName(), e);
                    }
                }
                System.out.println("Test passed: " + method.getName());
            }
        }
    }
}
