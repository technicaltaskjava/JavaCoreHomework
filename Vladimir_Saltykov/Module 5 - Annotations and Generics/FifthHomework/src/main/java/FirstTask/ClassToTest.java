package FirstTask;




public class ClassToTest {

    @TestInterface(ignore = true)
    public static void firstMethod() {

        System.out.println("First method was ignored");

    }

    @TestInterface(ignore = false)
    public void SecondMethod() {

        System.out.println("Second method isn't ignored");

    }



    @TestInterface(expected = NullPointerException.class)

    public void thirdMethod() {

        System.out.println("Null Pointed Exception was caught");

        if (true){
            throw new NullPointerException("NullPointed Exception for ever");
        }


    }



    }
