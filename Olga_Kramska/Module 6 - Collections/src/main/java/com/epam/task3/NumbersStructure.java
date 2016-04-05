package com.epam.task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Olga Kramska on 03-Apr-16.
 */
public class NumbersStructure<T extends Number> {
    private List<T> structure;

    public NumbersStructure() {
        structure = new ArrayList<>();
    }

    public NumbersStructure(T number) {
        this();
        structure.add(number);
    }

    public NumbersStructure(Collection<T> numbersCollection) {
        structure = new ArrayList<>(numbersCollection);
    }

    public boolean addNumber(T number) {
        structure.add(number);
        return true;
    }

    public boolean removeNumber(T number) {
        if (structure.contains(number)) {
            structure.removeAll(Collections.singleton(number));
            return true;
        }
        return false;
    }

    public T findClosestNumber(T number) {
        T closestNumber = structure.get(0);
        double difference = Math.abs(number.doubleValue() - structure.get(0).doubleValue());
        for (int i = 1; i < structure.size(); i++) {
            double diff = Math.abs(number.doubleValue() - structure.get(i).doubleValue());
            if (difference > diff) {
                difference = diff;
                closestNumber = structure.get(i);
            }
        }
        return closestNumber;
    }
}
