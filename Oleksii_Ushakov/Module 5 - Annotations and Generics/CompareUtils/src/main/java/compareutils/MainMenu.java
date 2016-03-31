package compareutils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Alexey Ushakov
 */
public class MainMenu {
    private static final int ARRAY_SIZE = 20;
    private static final int RANDOM_UPPER_BOUNDARY = 50;
    private static Integer[] testArray = new Integer[ARRAY_SIZE];

    private MainMenu() {
    }

    static class ComparatorInteger implements Comparator<Integer> {
        @Override
        public int compare(Integer integer, Integer t1) {
            if (integer > t1) {
                return 1;
            }
            if (integer < t1) {
                return -1;
            }
            return 0;
        }
    }

    private static void init() {
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            testArray[i] = random.nextInt(RANDOM_UPPER_BOUNDARY);
        }
    }

    public static void main(String[] args) {
        init();
        System.out.println("Test array initialized by random.nextInt(" + RANDOM_UPPER_BOUNDARY + ")");
        System.out.println("Test array " + Arrays.toString(testArray));
        System.out.println("\nUsing \'Comparable\'");
        System.out.println(" Maximum: " + CompareUtils.max(testArray));
        System.out.println(" Minimum: " + CompareUtils.min(testArray));
        System.out.println(" Median: " + CompareUtils.median(testArray));

        ComparatorInteger comparatorInteger = new ComparatorInteger();

        System.out.println("\nUsing \'Comparator\'");
        System.out.println(" Maximum: " + CompareUtils.max(testArray, comparatorInteger));
        System.out.println(" Minimum: " + CompareUtils.min(testArray, comparatorInteger));
        System.out.println(" Median: " + CompareUtils.median(testArray, comparatorInteger));
    }
}
