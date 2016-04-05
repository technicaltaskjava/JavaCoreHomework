package listInterface;

import java.util.*;

public class MyList implements List
    {
        private static final int DEFAULT_CAPACITY = 10;
        private static final Object[] EMPTY_ELEMENTDATA = {};
        private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

        private int size;
        Object[] array = new Object[DEFAULT_CAPACITY];

        public int size()
            {
                return this.size;
            }

        public boolean isEmpty()
            {
                return size == 0;
            }

        public boolean contains(Object o)
            {
                return indexOf(o) >= 0;
            }

        public MyList(Object[] ar)
            {
                this.array = ar;
            }
        public MyList() {
            this.array = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        }


        public MyList(int initialCapacity)
            {
                if (initialCapacity > 0)
                    {
                        this.array = new Object[initialCapacity];
                    }
                else
                    {
                        if (initialCapacity == 0)
                            {
                                this.array = EMPTY_ELEMENTDATA;
                            }
                        else
                            {
                                throw new IllegalArgumentException("Illegal Capacity: " +
                                        initialCapacity);
                            }
                    }
            }


        public Object[] toArray()
            {
                return Arrays.copyOf(array, size);
            }

        public Object[] toArray(Object[] arr)
            {
                Object[] temp = new Object[size()];
                for (int i = 0; i < arr.length; i++)
                    {
                        temp[i] = arr[i];
                    }
                return temp;
            }

        public boolean add(Object obj)
            {
                Object[] tmp = new Object[size() + 1];
                tmp[tmp.length - 1] = obj;
                for (int i = 0; i < array.length; i++)
                    {
                        tmp[i] = array[i];
                    }
                array = tmp;
                size++;
                return true;
            }


        public boolean remove(Object o)
            {
                int pos = 0;
                if (contains(o))
                    {
                        for (Object object : array)
                            {
                                if (object.equals(o))
                                    {
                                        break;
                                    }
                                pos++;
                            }
                        Object[] temp = new Object[array.length];
                        for (int stap = 0; stap < pos; stap++)
                            {
                                temp[stap] = array[stap];
                            }
                        for (int stap = pos + 1; stap < array.length; stap++)
                            {
                                temp[stap - 1] = array[stap];
                            }
                        array = temp;
                        size--;
                        return true;
                    }

                return false;
            }

        public boolean addAll(Collection c)
            {
                Object[] newCol = c.toArray();
                Object[] temp = new Object[array.length + newCol.length];
                for (int stap = 0; stap < array.length; stap++)
                    {
                        temp[stap] = array[stap];
                    }

                for (int stap = 0; stap < newCol.length; stap++)
                    {
                        temp[array.length + stap] = newCol[stap];
                    }
                array = temp;
                size += newCol.length;

                return size != 0;
            }

        public boolean addAll(int index, Collection c)
            {
                rangeCheckForAdd(index);
                Object[] newCol = c.toArray();
                Object[] temp = new Object[size + newCol.length];
                int pos = size - index;
                if (pos > 0)
                    {
                        for (int stap = 0; stap < index; stap++)
                            {
                                temp[stap] = array[stap];
                            }
                        for (int stap = 0; stap < newCol.length; stap++)
                            {
                                temp[index + stap] = newCol[stap];
                            }
                        array = temp;
                        size += newCol.length;
                    }


                return size != 0;
            }

        public void clear()
            {
                for (int stap = 0; stap < size; stap++)
                    array[stap] = null;
                size = 0;

            }

        public Object get(int index)
            {
                rangeCheck(index);
                return array[index];
            }

        public Object set(int index, Object element)
            {
                rangeCheck(index);
                Object oldValue = array[index];
                array[index] = element;
                return oldValue;
            }

        public void add(int index, Object element)
            {
                rangeCheckForAdd(index);
                Object[] temp = new Object[array.length + 1];
                temp[index] = element;
                for (int stap = 0; stap < index; stap++)
                    {
                        temp[stap] = array[stap];
                    }
                for (int stap = index; stap < array.length; stap++)
                    {
                        temp[stap + 1] = array[stap];
                    }
                array = temp;
                size++;

            }

        public Object remove(int index)
            {
                rangeCheck(index);
                Object[] temp = new Object[array.length - 1];
                Object res = array[index];
                for (int stap = 0; stap < index; stap++)
                    {
                        temp[stap] = array[stap];
                    }
                for (int stap = index + 1; stap < array.length; stap++)
                    {
                        temp[stap - 1] = array[stap];
                    }
                array = temp;

                return res;
            }

        public int indexOf(Object o)
            {
                if (o == null)
                    {
                        for (int stap = 0; stap < size; stap++)
                            if (array[stap] == null)
                                {
                                    return stap;
                                }
                    }
                else
                    {
                        for (int stap = 0; stap < size; stap++)
                            if (o.equals(array[stap]))
                                {
                                    return stap;
                                }
                    }
                return -1;
            }

        public int lastIndexOf(Object o)
            {
                if (o == null)
                    {
                        for (int stap = size - 1; stap >= 0; stap--)
                            if (array[stap] == null)
                                {
                                    return stap;
                                }
                    }
                else
                    {
                        for (int stap = size - 1; stap >= 0; stap--)
                            if (o.equals(array[stap]))
                                {
                                    return stap;
                                }
                    }
                return -1;
            }

        public Iterator iterator()
            {
                return new Itr();
            }

        private class Itr implements Iterator<Object>
            {
                int cursor;
                int lastRet = -1;


                public boolean hasNext()
                    {
                        return cursor != size;
                    }

                @SuppressWarnings("unchecked")
                public Object next()
                    {

                        int stap = cursor;
                        if (stap >= size)
                            {
                                throw new NoSuchElementException();
                            }
                        Object[] elementData = MyList.this.array;
                        if (stap >= elementData.length)
                            {
                                throw new ConcurrentModificationException();
                            }
                        cursor = stap + 1;
                        return  elementData[stap];
                    }
            }

        public boolean removeAll(Collection c)
            {
                Objects.requireNonNull(c);
                Object[] newCol = c.toArray();
                for(int stap = 0; stap < array.length; stap++)
                    {
                        for (int byStap = 0; byStap < newCol.length; byStap++)
                            {
                                if (array[stap].equals(newCol[byStap]))
                                    {
                                        remove(stap);
                                        break;

                                    }

                            }
                    }
                size-=newCol.length;


                return true;
            }

        public boolean containsAll(Collection c)
            {
                Objects.requireNonNull(c);
                Object[] newCol = c.toArray();
                for(int stap = 0; stap < newCol.length; stap++)
                    {
                        for (int byStap = 0; byStap < array.length; byStap++)
                            {
                                if (!newCol[stap].equals(array[byStap]))
                                    {
                                        return false;

                                    }
                            }
                    }



                return true;
            }

        private void rangeCheck(int index)
            {
                if (index >= size)
                    {
                        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
                    }
            }

        private void rangeCheckForAdd(int index)
            {
                if (index > size || index < 0)
                    {
                        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
                    }
            }








        private String outOfBoundsMsg(int index)
            {
                return "Index: " + index + ", Size: " + size;
            }

        public ListIterator listIterator(int index)
            {
                return null;
            }

        public List subList(int fromIndex, int toIndex)
            {
                return new MyList();
            }

        public ListIterator listIterator()
            {
                return null;
            }


        public boolean retainAll(Collection c)
            {
                return false;
            }




    }
