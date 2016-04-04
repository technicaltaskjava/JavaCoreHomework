import annotations.Test;

import java.lang.reflect.Method;

public class TestedClass {

    static void run(){
        Class tested = TestedClass.class;
        Method[] methods = tested.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(Test.class)){
                Test current = methods[i].getAnnotation(Test.class);
                if (current.ignore()){
                    System.out.println(methods[i].getName() + " - test ignored.");
                } else {
                    try {
                        Object testObj = tested.newInstance();
                        if((boolean)methods[i].invoke(testObj)){
                            System.out.println(methods[i].getName() + " - test passed.");
                        } else {
                            System.out.println(methods[i].getName() + " - test failed.");
                        }
                    } catch (Exception e){
                        if (e.getCause().getClass().equals(current.expected())){
                            System.out.println(methods[i].getName() + " - test passed.");
                        } else {
                            System.out.println(methods[i].getName() + " - test failed with an error: " + e.getCause().toString());
                        }
                    }
                }
            }
        }
    }

    int testedMethod(int a, int b){
        return a / b;
    }

    @Test
    boolean testedMethodTestEqualsError(){
        return testedMethod(5,0) == 0;
    }

    @Test
    boolean testedMethodTestEqualsCorrect(){
        return testedMethod(0,5) == 0;
    }

    @Test
    boolean testedMethodTestEqualsIncorrect(){
        return testedMethod(0,5) == 5;
    }

    @Test(ignore = true)
    boolean testedMethodTestIgnore(){
        return testedMethod(5,0) == 0;
    }

    @Test(expected = ArithmeticException.class)
    boolean testedMethodTestException(){
        return testedMethod(5,0) == 0;
    }

    @Test(ignore = true, expected = ArithmeticException.class)
    boolean testedMethodTestExceptionIgnore(){
        return testedMethod(5,0) == 0;
    }
}
