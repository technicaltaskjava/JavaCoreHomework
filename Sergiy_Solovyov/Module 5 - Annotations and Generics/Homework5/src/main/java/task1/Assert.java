package task1;


/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class Assert {


    public static void assertEquals(String message, Object o1, Object o2){
        if (!o1.equals(o2)) System.out.println(message);
        else System.out.println("Test passed");
    }

    public static void assertNotEquals(String message, Object o1, Object o2){
        if (!o1.equals(o2)) System.out.println("Test passed");
        else System.out.println(message);
    }
    public static void assertNotNull(String message, Object object) {
        if(object == null) {
            System.out.println(message);
        }else System.out.println("Test passed");
    }

    public static void assertNull(String message, Object object) {
        if(object != null) {
            System.out.println(message);
        }else System.out.println("Test passed");
    }
}
