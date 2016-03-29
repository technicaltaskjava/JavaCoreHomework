package com.epam.t03;
/*
Current implementation is based on lectors requirements:
1. Make private constructor for each type of tuple
2. Implement static method "create" which returns generic object
3. Each object must have getter without setter
 */
public class TupleClassMain {

    public static void mainMenu() {
        final String FIRST_TEST_ITEM = "test String";
        final Integer SECOND_TEST_ITEM = 100;
        final Character THIRD_TEST_ITEM = 'c';
        System.out.println("Tuples example on test data :");

        Triplet myTuple = Triplet.createTriplet(FIRST_TEST_ITEM, SECOND_TEST_ITEM, THIRD_TEST_ITEM);

        System.out.println("TRIPLET<A,B,C>");
        System.out.println("Type of A is : " + myTuple.getFirstType() +
                " and value is : " + myTuple.getFirstItem());
        System.out.println("Type of B is : " + myTuple.getSecondType() +
                " and value is : " + myTuple.getSecondItem());
        System.out.println("Type of C is : " + myTuple.getThirdType() +
                " and value is : " + myTuple.getThirdItem());
    }
}
