package com.kokhanyuk.collections.mylist;

import java.util.*;

/**
 * MyList
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyList<T> {
    private T[] list;
    private int counter;
    private static final int CAPACITY=10;

    public MyList() {
        list = (T[]) new Object[CAPACITY];
    }

    public MyList(int capacity) {
        list = (T[]) new Object[capacity];
    }


    public int size() {
        return counter;
    }

    public boolean isEmpty() {
        if (counter == 0) {
            return true;
        }
        return false;
    }

    public boolean contains(T o) {
        if (!isEmpty()) {
            for (T t : list) {
                if (o.equals(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean add(T o) {
        if (counter == list.length - 1) {
            list = Arrays.copyOf(list, list.length + 10);
        }
        list[counter] = o;
        counter++;
        return true;
    }

    public boolean remove(T o) {
        for (int i = 0; i < list.length; i++) {
            if (o.equals((T) list[i])) {
                list[i] = null;
                sort();
                return true;
            }
        }
        return false;
    }

    public void clear() {
        list = (T[]) new Object[10];
        counter=0;
    }

    public T get(int index) {
        if (index < list.length) {
            return list[index];
        }
        return null;
    }

    public void add(int index, T element) {
        if (index < list.length) {
            if (list[index] == null) {
                counter++;
            }
            list[index] = element;
        }

    }

    public T remove(int index) {
        if (index < list.length && list[index] != null) {
            T tmp = list[index];
            list[index] = null;
            return tmp;
        }
        return null;
    }

    public int indexOf(T o) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(o)) {
                return i;
            }
        }
        return 0;
    }

    public int lastIndexOf(T o) {
        for (int i = list.length - 1; i > 0; i--) {
            if (list[i]!=null&&list[i].equals(o)) {
                return i;
            }
        }
        return 0;
    }

    private void sort() {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j] == null) {
                    list[j] = list[j + 1];
                    list[j+1]=null;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (T t : list) {
            if (t != null) {
                sb.append(t.toString() + "\n");
            }
        }
        return sb.toString();
    }
}
