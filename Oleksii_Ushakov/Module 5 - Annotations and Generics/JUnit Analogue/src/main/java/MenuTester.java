import tester.Test;

import java.lang.reflect.Method;

/**
 * @author Alexey Ushakov
 */
public class MenuTester {
    public static void main(String[] args) {
        Class testedClass = TestedClass.class;
        Method[] methods = testedClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                if (annotation.ignore()) {
                    System.out.println("IGNORE\nMethod " + method.getName() + '\n');
                } else {
                    Class<? extends Throwable> expected = annotation.expected();
                    try {
                        method.invoke(testedClass.newInstance());
                        System.out.println("PASSED\nMethod " + method.getName() + '\n');
                    } catch (Exception e) {
                        String message = e.getCause().getMessage();
                        if (e.getCause().getClass() == expected) {
                            System.out.println("PASSED\nMethod " + method.getName());
                            System.out.println(" - Expected: " + expected);
                            System.out.println(" - Catch: " + e.getCause().getClass());
                        } else {
                            System.out.println("FAIL\nMethod " + method.getName());
                            if (expected != Test.NotException.class) {
                                System.out.println(" - Expected: " + expected);
                            }
                            System.out.println(" - Catch: " + e.getCause().getClass());
                        }
                        if (message != null) {
                            System.out.println(" - Message: " + message);
                        }
                        System.out.println();
                    }
                }
            } else {
                System.out.println("SKIPP\nMethod " + method.getName() + '\n');
            }
        }
    }
}
