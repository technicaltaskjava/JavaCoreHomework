package epam.com.task2;

import java.util.*;

/**
 * Created by Oleg on 05.04.2016.
 */
public class MyArrayListImpl<T> implements List<T>, java.io.Serializable {

    private transient Object[] elements;
    private int size;
    private int index = 0;

    public MyArrayListImpl() {
        this(10);
    }

    public MyArrayListImpl(int size) {
        this.elements = new Object[size];
        this.size = size;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (Object t :
                elements) {
            if (t.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<T> iterator() {

        return new Iterator<T>() {
            int iter = 0;

            public boolean hasNext() {
                if (size > iter + 1) {
                    return elements[iter + 1] != null;
                }
                return false;
            }

            public T next() {
                if (hasNext()) {
                    iter++;
                    return (T) elements[iter - 1];
                }
                return null;
            }
        };
    }

    public Object[] toArray() {
        return elements;
    }

    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) elements;
    }

    public boolean add(T t) {
        if (t == null) {
            return false;
        }
        if (index == elements.length) {
            Object[] temp = new Object[elements.length + 1];
            temp = elements.clone();
            elements = temp;
        }
        elements[elements.length - 1] = t;
        size = elements.length;
        return true;
    }

    public boolean remove(Object o) {
        for (int index = 0; index < size; index++)
            if (o.equals(elements[index])) {
                if (size - index - 1 > 0) {
                    System.arraycopy(elements, index + 1, elements, index, size - index - 1);
                    size = elements.length;
                    return true;
                }
            }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        Iterator<?> e = c.iterator();
        while (e.hasNext()) {
            if (!contains(e.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {

        Object[] a = c.toArray();
        int newSize = a.length;
        ensureCapacity(size + newSize);
        System.arraycopy(a, 0, elements, size, newSize);
        size += newSize;
        return newSize != 0;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] a = c.toArray();
        int newSize = a.length;
        ensureCapacity(size + newSize);
        if (size - index - 1 > 0) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        System.arraycopy(a, 0, elements, index, newSize);
        size += newSize;
        return newSize != 0;
    }

    public boolean removeAll(Collection<?> c) {

        int deleted = 0;
        Iterator<?> e = c.iterator();
        while (e.hasNext()) {
            if (contains(e.next())) {
                remove(e.next());
                size--;
                deleted++;
            }
        }
        return deleted != 0;
    }

    public boolean retainAll(Collection<?> c) {
        int deleted = 0;
        Iterator<?> e = c.iterator();
        while (e.hasNext()) {
            if (!contains(e.next())) {
                remove(e.next());
                size--;
                deleted++;
            }
        }
        return deleted == 0;
    }

    public void clear() {

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    public T get(int index) {
        if (index < size) {
            return (T) elements[index];
        }
        return null;
    }

    public T set(int index, T element) {
        if (index < size) {
            return (T) (elements[index] = element);
        }
        return null;
    }

    public void add(int index, T element) {

        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;

    }

    public T remove(int index) {
        T ret = (T) elements[index];
        if (size - index - 1 > 0) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        return ret;
    }

    public int indexOf(Object o) {

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return 0;
    }

    public int lastIndexOf(Object o) {
        int lastIndexOf = 0;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                lastIndexOf = i;
            }
        }
        return lastIndexOf;
    }

    public ListIterator<T> listIterator() {
        return createIterator(0);
    }

    public ListIterator<T> listIterator(int index) {
        return createIterator(index);

    }

    public List<T> subList(int fromIndex, int toIndex) {
        List<T> list = new ArrayList<T>();
        if (fromIndex < toIndex && toIndex < size) {
            for (int i = fromIndex; i < toIndex; i++) {
                list.add((T) elements[i]);
            }
            return list;
        }
        return null;
    }

    public void ensureCapacity(int minCapacity) {

        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {

            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }


            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    public ListIterator<T> createIterator(final int index) {
        return new ListIterator<T>() {

            int iteration = index;

            public boolean hasNext() {
                if (iteration < size && elements[iteration + 1] != null) {
                    return true;
                }
                return false;
            }

            public T next() {
                if (iteration + 1 < size) {
                    iteration++;
                    return (T) elements[iteration - 1];
                }
                return null;
            }

            public boolean hasPrevious() {
                if (iteration - 1 > 0) {
                    return true;
                }
                return false;
            }

            public T previous() {
                if (iteration - 1 > 0) {
                    iteration--;
                    return (T) elements[iteration];
                }
                return null;
            }

            public int nextIndex() {
                return iteration + 1;
            }

            public int previousIndex() {
                return iteration - 1;
            }

            public void remove() {
                if (size - iteration - 1 > 0) {
                    System.arraycopy(elements, iteration + 1, elements, iteration, size - iteration - 1);
                }

            }

            public void set(T t) {
                elements[iteration] = t;
            }

            public void add(T t) {
                add(t);
            }
        };
    }


}
