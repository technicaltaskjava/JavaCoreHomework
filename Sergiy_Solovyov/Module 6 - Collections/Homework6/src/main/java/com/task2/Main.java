package com.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * @author Sergey Solovyov
 */
public class Main {
    private Main(){}
    public static void main(String[] args) {
        MyArrayList<Integer>myArrayList = new MyArrayList<>();
        System.out.println("Empty? : " + myArrayList.isEmpty());
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        System.out.println(myArrayList.toString());
        System.out.println("Empty? : " + myArrayList.isEmpty());
        System.out.println("Size: " + myArrayList.size());
        System.out.println("Contains 3: " + myArrayList.contains(3));
        System.out.println("Contains -5: " + myArrayList.contains(-5));
        Iterator<Integer> iterator = myArrayList.iterator();
        System.out.println("Iteration with iterator: ");
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "  ");
        }
        System.out.println();
        System.out.println("MyArrayList to array: " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Removing number(index 0): ");
        myArrayList.remove(0);
        System.out.println("After removing: " + myArrayList.toString());
        System.out.println("Index of 4(indexOf): " + myArrayList.indexOf(4));
        System.out.println("Index of 4(lastIndexOf): " + myArrayList.lastIndexOf(4));
        System.out.println("Index of -6(indexOf): " + myArrayList.lastIndexOf(-6));
        List<Integer>subList = myArrayList.subList(1 , 3);
        System.out.println(subList);
        System.out.println("Creating ArrayList");
        ArrayList<Integer>arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        System.out.println("ArrayList: " + arrayList);
        System.out.println("RetainAll: " + myArrayList.retainAll(arrayList));
        System.out.println("After: " + myArrayList.toString());
        ArrayList<Integer>arrayList2 = new ArrayList<>();
        arrayList2.add(4);
        myArrayList.removeAll(arrayList2);
        System.out.println("After method removeAll: " + myArrayList.toString());
        System.out.println("ContainsAll: " + myArrayList.containsAll(arrayList2));

    }
}
