package com.kokhanyuk.javase05.myjunit.test;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TestAnnotationAnalyzer
 * <p/>
 * This class contains methods for starting methods contain annotations.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class TestAnnotationAnalyzer {
    static Logger logger = Logger.getLogger(TestAnnotationAnalyzer.class.getName());

    public void analyz(Class<?> testClass) {
        Method[] methods = testClass.getMethods();
        for (Method method : methods) {
            methodSearcher(method, testClass);
        }
    }

    private void methodSearcher(Method method, Class<?> testClass) {
        if (method.isAnnotationPresent(Test.class)) {
            Test test = method.getAnnotation(Test.class);
            boolean ignore = test.ignore();
            Class expected = test.expected();
            try {
                if (!ignore) {
                    method.invoke(testClass.newInstance());
                    logger.log(Level.INFO, "\nMethod " + method.getName() + " is passed Test.\n");
                } else {
                    logger.log(Level.INFO, "\nMethod " + method.getName() + " is ignored Test.\n");
                }
            } catch (Exception e) {
                logger.log(Level.INFO, "\nAnalysis of the received error:\n", e);
                exceptionCheck(e.getCause().getClass(), expected, method.getName());
            }
        }
    }

    private void exceptionCheck(Class actual, Class expected, String methodName) {
        if (actual == expected) {
            logger.log(Level.INFO, "\nTest " + methodName + " for an expected Exception is passed.\n");
        } else {
            logger.log(Level.INFO, "\nError " + methodName + " unknown Exception.\n");
        }
    }

}
