package task2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class CompareUtils {

    public static  <T extends Comparable>T  min( T[] array){
        if (array != null && array.length != 0){
            Arrays.sort(array);}
        else return null;

        return array[0];
    }
    public  static <T>T  min( T[] array, Comparator comparator){
        if (array != null && array.length != 0){
            Arrays.sort(array, comparator);}
        else return null;

        return array[0];
    }

    public  static <T extends Comparable>T  max( T[] array){
        if (array != null && array.length != 0){
            Arrays.sort(array);}
        else return null;

        return array[array.length - 1];
    }

    public static  <T>T  max( T[] array, Comparator comparator){
        if (array != null && array.length != 0){
            Arrays.sort(array, comparator);}
        else return null;

        return array[array.length - 1];
    }

    public static  <T extends Comparable>T  median( T[] array){
        if (array != null && array.length != 0){
            Arrays.sort(array);}
        else return null;

        return array[array.length/2];
    }

    public static  <T extends Comparable>T  median( T[] array, Comparator comparator){
        if (array != null && array.length != 0){
            Arrays.sort(array, comparator);}
        else return null;

        return array[array.length/2];
    }
}
