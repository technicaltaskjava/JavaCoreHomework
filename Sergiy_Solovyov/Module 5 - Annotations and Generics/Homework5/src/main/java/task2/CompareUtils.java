package task2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class CompareUtils {

    public static  <T extends Comparable>T  min( T[] array) throws WrongArgumentException {
        if (array != null && array.length != 0){
            Arrays.sort(array);}
        else throw new WrongArgumentException("Array does not contain objects");

        return array[0];
    }
    public  static <T>T  min( T[] array, Comparator comparator) throws WrongArgumentException {
        if (array != null && array.length != 0){
            Arrays.sort(array, comparator);}
        else throw new WrongArgumentException("Array does not contain objects");

        return array[0];
    }

    public  static <T extends Comparable>T  max( T[] array) throws WrongArgumentException {
        if (array != null && array.length != 0){
            Arrays.sort(array);}
        else throw new WrongArgumentException("Array does not contain objects");

        return array[array.length - 1];
    }

    public static  <T>T  max( T[] array, Comparator comparator) throws WrongArgumentException {
        if (array != null && array.length != 0){
            Arrays.sort(array, comparator);}
        else throw new WrongArgumentException("Array does not contain objects");

        return array[array.length - 1];
    }

    public static  <T extends Comparable>T  median( T[] array) throws WrongArgumentException {
        if (array != null && array.length != 0){
            Arrays.sort(array);}
        else throw new WrongArgumentException("Array does not contain objects");

        return array[array.length/2];
    }

    public static  <T extends Comparable>T  median( T[] array, Comparator comparator) throws WrongArgumentException {
        if (array != null && array.length != 0){
            Arrays.sort(array, comparator);}
        else throw new WrongArgumentException("Array does not contain objects");

        return array[array.length/2];
    }
}
