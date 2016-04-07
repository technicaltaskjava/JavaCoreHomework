package com.epam.javase06.task02;

public class MyList<E> {
    private E[] listArray;
    private static final int DEFAULT_SIZE = 16;
    private int maxSize;
    private int listSize;
    //private int size = 0;

    MyList(int size) {
        maxSize = size;
        listSize = 0;
        listArray = (E[])new Object[size];
    }

    MyList() {
        this(DEFAULT_SIZE);
    }

    public int size() {
        return listSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (listArray[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        listSize = 0;
    }

    public boolean add(E e) {
        if (listSize >= maxSize) {
            return false;
        }
        listArray[listSize++] = e;
        return true;
    }

    public E remove(int index) {
        if ((index < 0) || (index >= listSize)) {
            return null;
        }
        E del = listArray[index];
        for(int i = index; i < listSize - 1; i++)
            listArray[i] = listArray[i + 1];
        listSize--;
        return del;
    }

    public String toString() {

        for(E s : listArray) {
            if (s != null) {
                System.out.println(s.toString());
            }
        }
        return listArray.toString();
    }
}
