package com.epam.t02;

public class CompareUtilsMain {

    public static void mainMenu() {
        Integer[] TEST_INT_ARRAY = {7, 1, 5, 9, 2, 4};
        Float[] TEST_FLOAT_ARRAY = {7.3f, 1.5f, 0.81f, 5.345f};
        String[] TEST_STRING_ARRAY = {"xbc","bcd","Abc","sdf","vsd"};

        System.out.println("Example on integer, float and string arrays :");

        System.out.print("INTEGER ARRAY : ");
        for (Integer indexArray = 0; indexArray < TEST_INT_ARRAY.length; indexArray++) {
            System.out.print(String.valueOf(TEST_INT_ARRAY[indexArray]) + " ");
        }
        System.out.println();
        System.out.print("USING COMPARE TO : ");
        System.out.println("MIN: " + CompareUtils.minElement(TEST_INT_ARRAY) + " " +
                "MAX: " + CompareUtils.maxElement(TEST_INT_ARRAY) + " " +
                "MED: " + CompareUtils.medianElement(TEST_INT_ARRAY));
        System.out.print("USING COMPARATOR : ");
        System.out.println("MIN: " + CompareUtils.minElementByComparator(TEST_INT_ARRAY) + " " +
                "MAX: " + CompareUtils.maxElementByComparator(TEST_INT_ARRAY) + " " +
                "MED: " + CompareUtils.medianElementByComparator(TEST_INT_ARRAY));

        System.out.print("FLOAT ARRAY : ");
        for (Integer indexArray = 0; indexArray < TEST_FLOAT_ARRAY.length; indexArray++) {
            System.out.print(String.valueOf(TEST_FLOAT_ARRAY[indexArray]) + " ");
        }
        System.out.println();
        System.out.print("USING COMPARE TO : ");
        System.out.println("MIN: " + CompareUtils.minElement(TEST_FLOAT_ARRAY) + " " +
                "MAX: " + CompareUtils.maxElement(TEST_FLOAT_ARRAY) + " " +
                "MED: " + CompareUtils.medianElement(TEST_FLOAT_ARRAY));
        System.out.print("USING COMPARATOR : ");
        System.out.println("MIN: " + CompareUtils.minElementByComparator(TEST_FLOAT_ARRAY) + " " +
                "MAX: " + CompareUtils.maxElementByComparator(TEST_FLOAT_ARRAY) + " " +
                "MED: " + CompareUtils.medianElementByComparator(TEST_FLOAT_ARRAY));

        System.out.print("STRING ARRAY : ");
        for (Integer indexArray = 0; indexArray < TEST_STRING_ARRAY.length; indexArray++) {
            System.out.print(String.valueOf(TEST_STRING_ARRAY[indexArray]) + " ");
        }
        System.out.println();
        System.out.print("USING COMPARE TO : ");
        System.out.println("MIN: " + CompareUtils.minElement(TEST_STRING_ARRAY) + " " +
                "MAX: " + CompareUtils.maxElement(TEST_STRING_ARRAY) + " " +
                "MED: " + CompareUtils.medianElement(TEST_STRING_ARRAY));
        System.out.print("USING COMPARATOR : ");
        System.out.println("MIN: " + CompareUtils.minElementByComparator(TEST_STRING_ARRAY) + " " +
                "MAX: " + CompareUtils.maxElementByComparator(TEST_STRING_ARRAY) + " " +
                "MED: " + CompareUtils.medianElementByComparator(TEST_STRING_ARRAY));
    }
}
