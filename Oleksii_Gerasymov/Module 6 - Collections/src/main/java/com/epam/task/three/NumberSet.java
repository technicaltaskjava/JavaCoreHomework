package com.epam.task.three;

import java.util.HashSet;
import java.util.Iterator;

import static java.lang.Math.abs;

public class NumberSet<T extends Number> {
    HashSet<T> myHashSet;

    public NumberSet() {
        myHashSet =  new HashSet();
    }

    public int getLength() {
        return this.myHashSet.size();
    }

    public String getSet() {
        String resultString = "";
        Iterator<T> setIterator = myHashSet.iterator();
        while (setIterator.hasNext()) {
            resultString += setIterator.next().toString() + ", ";
        }
        return resultString;
    }

    public void addElement(T newElement) {
        myHashSet.add(newElement);
    }

    public void removeElement(T newElement) {
        myHashSet.remove(newElement);
    }

    public T seekNear(T newElement) {
        T resultElement = newElement;
        double nearValue = 0.0f;
        double currentValue;
        Iterator<T> setIterator = myHashSet.iterator();
        while (setIterator.hasNext()) {
            T currentElement = setIterator.next();

            if (Float.floatToRawIntBits((float) nearValue) == 0) {
                nearValue = abs(currentElement.doubleValue() - newElement.doubleValue());
                resultElement = currentElement;
            }

            if (currentElement == newElement) {
                return currentElement;
            }

            currentValue = abs(currentElement.doubleValue() - newElement.doubleValue());
            if (currentValue < nearValue) {
                nearValue = currentValue;
                resultElement = currentElement;
            }
        }
        return resultElement;
    }
}
