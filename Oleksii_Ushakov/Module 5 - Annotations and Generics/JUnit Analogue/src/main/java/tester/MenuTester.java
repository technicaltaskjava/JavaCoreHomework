package tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Alexey Ushakov
 */
public class MenuTester {
    private MenuTester() {
    }

    public static void checkMethod(Method method, Object classInstance)
            throws ReflectiveOperationException {
        if (method.isAnnotationPresent(Test.class)) {
            Test annotation = method.getAnnotation(Test.class);
            Class<? extends Throwable> expected = annotation.expected();
            try {
                if (annotation.ignore()) {
                    System.out.println("\nIGNORE\nMethod " + method.getName() + '\n');
                } else {
                    method.invoke(classInstance);
                    System.out.println("\nPASSED\nMethod " + method.getName() + '\n');
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                if (e.getCause().getClass() == expected) {
                    System.out.println("\nPASSED\nMethod " + method.getName());
                    System.out.println(" - Expected: " + expected);
                } else if (expected != Test.NotThrow.class) {
                    System.out.println("\nFAIL\nMethod " + method.getName());
                    System.out.println(" - Expected: " + expected);
                } else {
                    System.out.println("\nFAIL\nMethod " + method.getName());
                }
                System.out.println(" - Catch: " + e.getCause().getClass());
                throw e;
            }
        } else {
            System.out.println("\nSKIPP\nMethod " + method.getName() + '\n');
        }
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        Class testedClass = TestedClass.class;
        Method[] methods = testedClass.getDeclaredMethods();
        for (Method method : methods) {
                checkMethod(method, testedClass.newInstance());
        }
    }
}
