package com.epam.javase06.task03;

import java.util.Iterator;
import java.util.LinkedList;

public class NumberData<T extends Number> {
    private LinkedList<T> numbersList;

    public NumberData() {
        numbersList = new LinkedList<>();
    }

    public void addNumber(T number) {
       numbersList.add(number);
    }

    public void addNumberByIndex(int index, T number) {
        numbersList.add(index, number);
    }

    public void removeNumber(T number) {
        numbersList.remove(number);
    }

    public void removeNumberByIndex(int index) {
        numbersList.remove(index);
    }

    public T searchNearestNumber(T requiredNumber) {
        if (numbersList.contains(requiredNumber)) {
            return requiredNumber;
        }
        Iterator<T> iterator = numbersList.iterator();
        double module = 0;
        int currentIndex = 0;
        int requiredIndex = 0;

        while (iterator.hasNext()) {
            T currentNumber = iterator.next();
            double tmpModule = Math.abs(currentNumber.doubleValue() - requiredNumber.doubleValue());
            if (module < tmpModule) {
                requiredIndex = currentIndex;
            }
            currentIndex++;
        }
        return numbersList.get(requiredIndex);
    }

    @Override
    public String toString() {
        return numbersList.toString();
    }
}
