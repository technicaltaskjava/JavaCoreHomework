package Task1;


import Task1.Annotations.Test;
import Task1.MyClassesForTesting.Actor;
import junit.framework.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oleg on 27.03.2016.
 */
public class Run {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        performActorTest("Task1.MyClassesForTesting.Actor");

    }

    public static void performActorTest(String className) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Actor actor = (Actor) createClass(className);

        for (Method method :
                actor.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class) && !method.getAnnotation(Test.class).ignore()) {
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }

                if (method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).expected().equals("")) {

                        method.invoke(actor);

                }else {

                    try {

                        method.invoke(actor);

                    } catch (Throwable e) {

                        org.junit.Assert.assertTrue(e.getCause().getClass().getSimpleName().equals("MyException"));
                        System.out.println("Exception throwing tested good!" );

                    }

                }


            } else if (method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).ignore()) {
                System.out.println("Method: " + method.getName() + " not tested");
            }
        }
    }

    public static Object createClass(String className) throws NoSuchMethodException, InvocationTargetException {

        Class<?> c = null;
        try {
            c = Class.forName(className);
            Constructor<?> constructor = c.getConstructor();
            Object actor = constructor.newInstance();
            return actor;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
