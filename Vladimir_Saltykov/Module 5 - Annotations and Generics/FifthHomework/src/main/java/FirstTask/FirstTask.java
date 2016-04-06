package FirstTask;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FirstTask {


    public static void main(String[] args) {
        process(new ClassToTest());
    }


    public static void process(Object object) {
        ClassToTest classToTest = new ClassToTest();
        Class<?> clas = classToTest.getClass();

        for (Method method : clas.getDeclaredMethods()) {
            TestInterface test = method.getAnnotation(TestInterface.class);
            if (test != null && !test.ignore()) {
                try {
                    method.invoke(object);
                } catch (InvocationTargetException e) {
                }
                catch (IllegalAccessException r){
                }
                System.out.println("Test completed: " + method.getName());
            }else {
                ClassToTest.firstMethod();
            }
                }

            }
        }




