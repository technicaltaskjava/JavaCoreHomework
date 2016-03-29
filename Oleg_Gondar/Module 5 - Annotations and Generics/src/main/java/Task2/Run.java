package Task2;

/**
 * Created by O.Gondar on 28.03.2016.
 */
public class Run {

    public static void main(String[] args) {

        String[] s = {"aaaa", "bbbb", "cccc"};

        Integer[] i = {1111, 2222, 3333, 4444, 5555, 6666, 7777};

        runMethods(s);
        runMethods(i);


    }

    public static <T extends Comparable> void runMethods(T[] t) {

        System.out.println("Finding values for: " + t.getClass().getSimpleName());
        System.out.println("Max is: " + CompareUtils.minMax(t, false).getValue());
        System.out.println("Min is: " + CompareUtils.minMax(t, true).getValue());
        System.out.println("Median is: " + CompareUtils.median(t).getValue());
        System.out.println("____________________________________________________");
        System.out.println("Work with comparator");
        System.out.println("Finding values for: " + t.getClass().getSimpleName());
        System.out.println("Max is: " + CompareUtils.sortWithComparator(t, new MyComparator(), "max").getValue());
        System.out.println("Min is: " + CompareUtils.sortWithComparator(t, new MyComparator(), "min").getValue());
        System.out.println("Median is: " + CompareUtils.sortWithComparator(t, new MyComparator(), "median").getValue());
        System.out.println("____________________________________________________");

    }

}
