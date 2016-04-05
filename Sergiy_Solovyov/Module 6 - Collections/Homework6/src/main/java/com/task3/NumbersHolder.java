package com.task3;

import java.util.*;

/**
 * @author Sergey Solovyov
 */
public class NumbersHolder<T extends Number> {

    private ArrayList<T> numbers = new ArrayList<>();

    public boolean addNumber(T number){

        return numbers.add(number);
    }

    public void removeByIndex(int index){
        numbers.remove(index);
    }

    public void removeByNumber(T number){
        numbers.remove(number);
    }

    public void printClosestNumber(T number){
        System.out.println("Closest number to "+ number + ": "+ findClosestNumber(number));
    }

    public T findClosestNumber(T number){
        if (numbers.contains(number))
             return number;
        double result = Double.MAX_VALUE;
        Iterator<T> itr = numbers.iterator();
        T toReturn = null;
        while (itr.hasNext()){
            T t = itr.next();
            double iteration = Math.abs(t.doubleValue() - number.doubleValue());
            if (iteration < result) {
                result = iteration;
                toReturn = t;
                continue;}
         }
        return toReturn;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
