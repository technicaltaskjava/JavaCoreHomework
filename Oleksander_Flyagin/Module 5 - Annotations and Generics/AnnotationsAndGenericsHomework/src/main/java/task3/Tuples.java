package task3;


import java.util.Iterator;

public class Tuples<T> implements Iterable<T>
    {
        private T[] tuples = null;

        public void add(T tuple)
            {
                T[] temp = (T[])new   Object  [size() + 1];
                temp[temp.length-1] = tuple;
                for (int stap = 0; stap < size(); stap++)
                    {
                        temp[stap] = tuples[stap];
                    }
                tuples = temp;

            }

        public void delete(T tuple)
            {
                int index = findIndex(tuple);

                T [] temp =  (T[])new   Object  [size() + 1];
                for (int stap = 0; stap < index; stap++)
                    {
                                temp[stap] = tuples[stap];
                                stap++;
                    }
                for (int byStap = index+1; byStap<size(); byStap++)
                    {
                        temp[byStap-1]=tuples[byStap];
                    }
                tuples=temp;

            }
      public T getTuple(int index)
          {
              return tuples[index];
          }


        public int size()
            {
                if(tuples ==null)
                    {
                       return 0;
                    }
                return tuples.length;
            }

        private int findIndex(T tuple)
            {
                int index = -1;
                for (int stap = 0; stap < tuples.length; stap++)
                    {
                        if (tuple.equals(tuples[stap]))
                            {
                                index =  stap;
                                break;
                            }
                    }
                return index;
            }


        @Override
        public Iterator<T> iterator()
            {
                return (Iterator<T>) new AlIter(tuples);
            }

        class AlIter implements Iterator<T>
            {
                T[] rr=null;
                int index = 0;

                public AlIter(T[] tuples)
                    {
                        this.rr = tuples;
                    }
                @Override
                public boolean hasNext()
                    {
                        return index < size();
                    }
                @Override
                public T next()

                    {
                        return tuples[index++];
                    }
            }

    }
