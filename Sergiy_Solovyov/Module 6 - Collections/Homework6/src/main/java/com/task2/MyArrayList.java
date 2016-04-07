package com.task2;

import java.util.*;

/**
 * @author Sergey Solovyov
 */
public class MyArrayList<T> implements List<T> {

    private static final String BOUND_EXC = "ArrayIndexOutOfBound";
    private static final String NEG_VALUE = "Negative Value";
    private static final int START_SIZE = 8;
    private Object [] data;
    private int index;
    private int size;

    public MyArrayList(){
        this.data = new Object[START_SIZE];
        this.size = START_SIZE;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        for(Object o: data){
            if (o != null)
                return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null){
            for(int i = 0; i < index; i++){
                if (data[i] == null)
                    return true;
            }
            return false;
        }
        for(int i = 0; i < index; i++){
            if (o.equals(data[i]))
                return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iterIndex = 0;

            @Override
            public boolean hasNext() {
                return  iterIndex < index;
            }

            @Override
            public T next() {
                if (iterIndex > index)
                    throw new NoSuchElementException();
                return (T)data[iterIndex++];
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[index];
        for (int i = 0;i < index;i++){
            objects[i] = data[i];
        }
        return objects;
    }


    @Override
    public boolean add(Object o) {
        if(index == size - 1){
            increaseSizeAndReallocate();
        }
        data[index] = o;
        index++;
        return true;
    }
    private void increaseSizeAndReallocate() {
        size = 2 * size;
        Object [] newData = new Object[size];
        for(int i = 0; i < data.length; i++){
            newData [i] = data[i];
        }
        this.data = newData;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < index; i++) {
                if (data[i] == null) {
                    replace(i);
                    index--;
                    return true;
                }
            }
            return false;
        }
        for (int i = 0; i < index; i++){
            if (o.equals(data[i])){
                replace(i);
                index-- ;
                return true;
            }
          }
        return false;
    }
private void replace(int i){
    for (int x = i; x < this.data.length - 1; x++) {
        data[x] = data[x + 1];
    }
}
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if(!isValidated(c))
            return false;
          for(T t: c){
               add(t);
          }
          return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if(!isValidated(c))
            return false;
        int ind = index;
        for(T t: c){
            add(++ind, t);
        }
        return false;
    }

    @Override
    public void clear() {
      for (int i = 0; i < index; i++)
      data[i] = null;
        index = 0;
    }

    @Override
    public T get(int index)  {
        rangeCheck(index);
        return (T)data[index];
    }
    private void rangeCheck(int index) {
        if( index > this.index - 1){
            throw new IndexOutOfBoundsException(BOUND_EXC);
        }
        if(index < 0){
            throw new IndexOutOfBoundsException(NEG_VALUE);
        }
    }

    @Override
    public T set(int index, Object element) {
        rangeCheck(index);
        T t = (T)data[index];
        data[index] = element;
        return t;
    }

    @Override
    public void add(int index, Object element) {
        rangeCheck(index);
        if(this.index == size - 1){
            increaseSizeAndReallocate();
        }
        for(int x = this.index; x >= index; x--){
            data[x + 1] = data[x];
        }
        data[index] = element;
        this.index++ ;
    }

    @Override
    public T remove(int ind) {
        rangeCheck(ind);
        T t = (T) data[index];

        for(int x = ind; x < this.data.length - 1; x++){
            data[x] = data[x + 1];
        }
        index-- ;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < index; i++) {
                if (data[i] == null) {
                   return i;
                }
            }
            return -1;
        }
        for(int i = 0; i < index; i++){
            if (o.equals(data[i]))
                return i;
        }
        return  -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = index -1 ; i >= 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for(int i = index -1 ; i >= 0; i--){
            if (o.equals(data[i]))
                return i;
        }
        return  -1;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        List<T> list = new MyArrayList<>();
        if (fromIndex <= toIndex){
        for (int i = fromIndex; i <= toIndex; i++){
            list.add((T)data[i]);
         }
        }
        else throw new IllegalArgumentException("toIndex must be bigger than fromIndex");
        return list;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(!isValidated(c))
            return false;
        int x = 0;
        for (int i = 0;  i < index; i++){
            if (c.contains(data[i])){
                data[x++] = data[i];
            }}
        if (x != index) {
            index = x;
            return true;
        } else {
            return false;
        }
    }


    private boolean isValidated(Collection c){

        return !(c == null || c.isEmpty());
    }
    @Override
    public boolean removeAll(Collection c) {
        if(!isValidated(c))
            return false;
        int x = 0;
         for (int i = 0;  i < index; i++){
                 if (!c.contains(data[i])){
                    data[x++] = data[i];
                    }}
         if (x != index) {
             index = x;
                 return true;
              } else {
                  return false;
              }
          }


    @Override
    public boolean containsAll(Collection<?> c) {
        if(!isValidated(c))
            return false;
        boolean flag = true;
        for (Object o: c){
            for (int i = 0;  i < index; i++){
            if (o.equals(data[i])){
                flag = true;
                break;}
            else flag = false;
            }
            if (flag)
                continue;
            else return flag;
        }

        return true;
    }
    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size)
            return Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < index; i++){
         sb.append(data[i]);
         sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
}
