package Task1;


import Task1.Annotations.Test;
import Task1.MyClassesForTesting.Actor;

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
                testMethod(method, actor);
            } else if (method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).ignore()) {
                System.out.println("Method " + method.getName() + " ignored");
            }
        }
    }

    public static void testMethod(Method method, Actor actor) throws InvocationTargetException, IllegalAccessException {

        switch (method.getAnnotation(Test.class).description().toString()) {
            case "Actor first name": {
                method.invoke(actor, "First");
                assertTrue(actor.getActorFirstName().equals("First"));
                System.out.println("Actor first name test passed");
            }
            break;
            case "Actor last name": {
                method.invoke(actor, "Last");
                assertTrue(actor.getActorLastName().equals("Last"));
                System.out.println("Actor last name test passed");
            }
            break;
            case "work with exception": {
                try {
                    method.invoke(actor);
                } catch (Exception e) {
                    if (e.getCause().getClass().toString().contains(method.getAnnotation(Test.class).expected()))
                        System.out.println("Actor exception test passed");
                }
            }
            break;

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
