package javase.t01.tester;

import javase.t01.annotation.Test;
import java.lang.reflect.Method;

/**
 * Class Tester for analysis Test class with reflection
 * Created by Yury Vislobodsky on 24.03.2016.
 */
public class Tester {
    private void myAssert(boolean expression, String message) {
        if (!expression) {
            System.out.print("failed: ");
            System.out.println(message);
            System.exit(0);
        } else {
            System.out.println("done");
        }
    }

    public void analyze(Object object) {
        Class someClass = object.getClass();
        for (Method method : someClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                System.out.print("Test for method " + method.getName() + " - ");
                if (!annotation.ignore()) {
                    try {
                        method.invoke(object);
                        System.out.println("done");
                    } catch (Exception e) {
                        myAssert(e.getCause().getClass().equals(annotation.expected()),
                                e.getCause().toString());
                    }
                } else {
                    System.out.println("ignored");
                }
            }
        }
    }
}
