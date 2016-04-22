package task1;

import java.util.*;


/**
 * Created by Oleg on 16.04.2016.
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        int startNumber;
        int endNumber;
        do {
            System.out.println("введи числа, неотрицательные, сначала начало диапазона, потом конец. \n Первое число обязательно меньше второго");
            startNumber = scanner.nextInt();
            endNumber = scanner.nextInt();
        }while (startNumber <=0 && endNumber <= 0 && startNumber > endNumber);


        int manyCollectionsWinsCount = 0;
        int oneCollectionWinsCount = 0;

        for (int i = 0; i < 100; i++){
            long temp = findWithDifferentCollections(startNumber, endNumber) - findWithOneCollection(startNumber, endNumber);
            if(temp > 0){
                oneCollectionWinsCount++;
            }else if(temp <0){
                manyCollectionsWinsCount++;
            }
        }

        System.out.println(manyCollectionsWinsCount - oneCollectionWinsCount > 0 ? "Many faster after 100 runs " + manyCollectionsWinsCount + ":" + oneCollectionWinsCount : "One faster after 100 runs " + oneCollectionWinsCount + ":" + manyCollectionsWinsCount);

    }


    public static long findWithDifferentCollections(int startNumber, int endNumber) throws InterruptedException {

        int diapazone = startNumber - endNumber;

        TreeSet<Integer> simpleNumbers = new TreeSet();
        TreeSet<Integer> simpleNumbers1 = new TreeSet();
        TreeSet<Integer> simpleNumbers2 = new TreeSet();
        TreeSet<Integer> simpleNumbers3 = new TreeSet();
        TreeSet<Integer> simpleNumbers4 = new TreeSet();

        Thread find1 = new Thread(new FindngSimpleNumbers(startNumber, startNumber+=diapazone / 4, simpleNumbers1));
        Thread find2 = new Thread(new FindngSimpleNumbers(startNumber+1, startNumber+=diapazone / 4, simpleNumbers2));
        Thread find3 = new Thread(new FindngSimpleNumbers(startNumber+1, startNumber+=diapazone / 4, simpleNumbers3));
        Thread find4 = new Thread(new FindngSimpleNumbers(startNumber+1, startNumber+diapazone / 4, simpleNumbers4));

        long startTime = System.nanoTime();
        find1.start();
        find2.start();
        find3.start();
        find4.start();
        find1.join();
        find2.join();
        find3.join();
        find4.join();

        simpleNumbers.addAll(simpleNumbers1);
        simpleNumbers.addAll(simpleNumbers2);
        simpleNumbers.addAll(simpleNumbers3);
        simpleNumbers.addAll(simpleNumbers4);

        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

        return endTime - startTime;
    }



    public static long findWithOneCollection(int startNumber, int endNumber) throws InterruptedException {

        TreeSet<Integer> simpleNumbers = new TreeSet();

        int diapazone = startNumber - endNumber;

        Thread find1 = new Thread(new FindngSimpleNumbers(startNumber, startNumber+=diapazone / 4, simpleNumbers));
        Thread find2 = new Thread(new FindngSimpleNumbers(startNumber+1, startNumber+=diapazone / 4, simpleNumbers));
        Thread find3 = new Thread(new FindngSimpleNumbers(startNumber+1, startNumber+=diapazone / 4, simpleNumbers));
        Thread find4 = new Thread(new FindngSimpleNumbers(startNumber+1, startNumber+diapazone / 4, simpleNumbers));

        long startTime = System.nanoTime();
        find1.start();
        find2.start();
        find3.start();
        find4.start();
        find1.join();
        find2.join();
        find3.join();
        find4.join();

        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

       return endTime - startTime;

    }
}
