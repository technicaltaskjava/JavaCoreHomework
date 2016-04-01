package edu.task1;


import edu.task1.annotations.Test;
import edu.task1.mytestingclasses.Actor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * Created by Oleg on 27.03.2016.
 */
public class Run {

    private Run() {
    }

    public static void main(String[] args) throws ReflectiveOperationException {


        try {
            performActorTest("edu.task1.mytestingclasses.Actor");
        } catch ( ReflectiveOperationException  e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    private static void performActorTest(String className) throws ReflectiveOperationException {

        Actor actor = (Actor) createClass(className);

        for (Method method :
                actor.getClass().getMethods()) {
            if (method.isAnnotationPresent(Test.class) && !method.getAnnotation(Test.class).ignore()) {

                try {

                    method.invoke(actor);

                } catch (ReflectiveOperationException e) {

                    org.junit.Assert.assertTrue(e.getCause() instanceof MyException);
                    System.out.println("Exception throwing test passed!");

                }

            } else if (method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).ignore()) {
                System.out.println("Method: " + method.getName() + " not tested");
            }
        }
    }

    public static Object createClass(String className) throws ReflectiveOperationException {

        Class<?> c = null;
        try {
            c = Class.forName(className);
            Constructor<?> constructor = c.getConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}