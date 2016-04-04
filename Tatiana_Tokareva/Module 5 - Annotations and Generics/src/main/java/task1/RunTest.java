package task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTest {
    public void startTest() {
        Object object = null;
        try {
            Class<?> clazz = Class.forName("task1.MyTest");
            object = clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (object != null) {
            Method[] testMethods = object.getClass().getDeclaredMethods();
            for (Method method : testMethods) {
                if (method.isAnnotationPresent(Test.class)) {
                    String methodName = method.getName();
                    Test annotation = method.getAnnotation(Test.class);
                    boolean ignore = annotation.ignore();
                    if (!ignore) {
                        Class<? extends Throwable> expected = annotation.expected();
                        try {
                            method.invoke(object, null);
                            if (expected != Test.None.class) {
                                throw new InvocationTargetException(new AssertionException(String.format("Expected %s", expected)));
                            } else {
                                System.out.println(String.format("test for method '%s' is done", methodName));
                            }
                        } catch (InvocationTargetException e) {
                            if (e.getCause().getClass() == expected) {
                                System.out.println(String.format("test for method '%s' is done", methodName));
                            } else {
                                System.out.println(String.format("test for method '%s' is fail", methodName));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(String.format("test for method '%s' is ignor", methodName));
                    }
                }
            }
        }
    }
}
