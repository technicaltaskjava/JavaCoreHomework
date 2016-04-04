package javase.t03.numbers;

import java.util.*;

/**
 * List of Numbers
 * Created by Yury Vislobodsky on 01.04.2016.
 */
public class Numbers<T extends Number> {
    private LinkedList<T> elements;

    public Numbers() {
        elements = new LinkedList<>();
    }

    public T searchElement(final T element) {
        if (elements.contains(element)) {
            return element;
        } else {
            return Collections.min(elements, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return Double.compare(Math.abs(o1.doubleValue() - element.doubleValue()),
                            Math.abs(o2.doubleValue() - element.doubleValue()));
                }
            });
        }
    }

    public void addElement(T element) {
        elements.addLast(element);
    }

    public void addElementByIndex(int index, T element) {
        elements.add(index, element);
    }

    public void deleteElement(T element) {
        elements.remove(element);
    }

    public void deleteElementByIndex(int index) {
        elements.remove(index);
    }

    public int getLength() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
