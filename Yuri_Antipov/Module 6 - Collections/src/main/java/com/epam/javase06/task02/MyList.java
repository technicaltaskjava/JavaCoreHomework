package com.epam.javase06.task02;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<E> implements List<E> {

    private E listArray[];
    private static final int defaultSize = 16;
    private int maxSize;
    private int listSize;
    private int curr;

    MyList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[])new Object[size];
    }

    MyList() {
        this(defaultSize);
    }


    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        listSize = 0;
    }

    @Override
    public boolean add(E e) {
        if (listSize >= maxSize) {
            return false;
        }
        listArray[listSize++] = e;
        return true;
    }

    @Override
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

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
