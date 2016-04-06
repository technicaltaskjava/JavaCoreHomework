package com.epam.task2;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Yuriy Krishtop on 04.04.2016.
 */
public class MyList {
    private Object[] myArray;

    public MyList() {
        this(10);
    }

    public MyList(int length) {
        myArray = new Object[length];
    }

    public int size() {
        int size;
        if (myArray == null) {
            size = 0;
        } else {
            int countNull = 0;
            for (int i = myArray.length - 1; i >= 0; i--) {
                if (myArray[i] == null) {
                    countNull++;
                } else {
                    break;
                }
            }
            size = myArray.length - countNull;
        }
        return size;
    }

    public void ensureCapacity(int capacity) {
        if (myArray.length < capacity) {
            int newCapacity = (capacity * 3) / 2 + 1;
            Object[] tmpArray = new Object[newCapacity];
            System.arraycopy(myArray, 0, tmpArray, 0, myArray.length);
            myArray = tmpArray;
        }
    }

    public boolean isEmpty() {
        for (Object o : myArray) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Object o) {
        for (Object element : myArray) {
            if ((element != null) && element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        if (size() == 0) {
            return new Object[0];
        } else {
            Object[] tmpArray = new Object[size()];
            System.arraycopy(myArray, 0, tmpArray, 0, size());
            return tmpArray;
        }
    }

    public boolean add(Object o) {
        int oldSize = size();
        ensureCapacity(size() + 1);
        myArray[size()] = o;
        return size() != oldSize;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if ((myArray[i] != null) && myArray[i].equals(o)) {
                int numMoved = size() - i - 1;
                System.arraycopy(myArray, i + 1, myArray, i, numMoved);
                myArray[size() - 1] = null;
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Collection c) {
        int oldSize = size();
        for (Object o : c) {
            ensureCapacity(size() + 1);
            add(o);
        }
        return size() != oldSize;
    }

    public boolean addAll(int index, Collection c) {
        int oldSize = size();
        Iterator iterator = c.iterator();
        int shift = 0;
        while (iterator.hasNext()) {
            ensureCapacity(size() + 1);
            add(index + shift, iterator.next());
            shift++;
        }
        return size() != oldSize;
    }

    public void clear() {
        for (int i = 0; i < size(); i++) {
            myArray[i] = null;
        }
    }

    public Object get(int index) {
        return myArray[index];
    }

    public Object set(int index, Object element) {
        if ((index < size()) && (index >= 0)) {
            Object replased = myArray[index];
            myArray[index] = element;
            return replased;
        } else {
            return null;
        }
    }

    public void add(int index, Object element) {
        if ((index < size()) && (index >= 0)) {
            ensureCapacity(size() + 1);
            System.arraycopy(myArray, index, myArray, index + 1, size() - index);
            myArray[index] = element;
        }
    }

    public Object remove(int index) {
        if ((index < size()) && (index >= 0)) {
            int numMoved = size() - index - 1;
            System.arraycopy(myArray, index + 1, myArray, index, numMoved);
            Object moved = myArray[size() - 1];
            myArray[size() - 1] = null;
            return moved;
        } else {
            return null;
        }
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if ((myArray[i] != null) && myArray[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size(); i >= 0; i--) {
            if ((myArray[i] != null) && myArray[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public MyList subList(int fromIndex, int toIndex) {
        MyList tmpArray = new MyList(toIndex - fromIndex + 1);
        for (int i = fromIndex; i <= toIndex; i++) {
            tmpArray.add(myArray[i]);
        }
        return tmpArray;
    }

    public boolean retainAll(Collection c) {
        int oldSize = size();
        Object[] tmpArray = new Object[size()];
        int j = 0;
        for (int i = 0; i < oldSize; i++) {
            for (Object o : c) {
                if ((myArray[i] != null) && myArray[i].equals(o)) {
                    tmpArray[j] = myArray[i];
                    j++;
                }
            }
        }
        myArray = tmpArray;
        return size() != oldSize;
    }

    public boolean removeAll(Collection c) {
        int oldSize = size();
        for (Object o : c) {
            while (contains(o)) {
                remove(o);
            }
        }
        return size() != oldSize;
    }

    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public Object[] toArray(Object[] a) {
        if (size() == 0) {
            return new Object[0];
        } else {
            int countNewElement = 0;
            for (int i = 0; i < size(); i++) {
                if (myArray[i].getClass() == a.getClass()) {
                    countNewElement++;
                }
            }
            Object[] tmpArray = new Object[countNewElement];
            int j = 0;
            for (int i = 0; i < size(); i++) {
                if (myArray[i].getClass() == a.getClass()) {
                    tmpArray[j] = myArray[i];
                    j++;
                }
            }
            return tmpArray;
        }
    }

    @Override
    public String toString() {
        String str = "[";
        for (Object o : myArray) {
            str += o + ", ";
        }
        return str.substring(0, str.length() - 2) + "]";
    }
}
