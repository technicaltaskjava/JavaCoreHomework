package task2;

import java.util.Comparator;

import static java.util.Objects.compare;


public class Task2a
    {
        public static <T extends Comparable> T max(final T[] array)
            {
                T max = array[0];
                for (int i = 1; i < array.length; i++)
                    {
                        if (max.compareTo(array[i]) == -1)
                            {
                                max = array[i];
                            }
                    }
                return max;
            }


        public static <T> T max(final T[] array, final Comparator<? super T> comparator)
            {
                T max = array[0];
                for (int stap = 1; stap < array.length; stap++)
                    {
                        if (compare(max, array[stap], comparator) == -1)
                            {
                                max = array[stap];
                            }
                    }
                return max;
            }


        public static <T extends Comparable> T min(final T[] array)
            {
                T min = array[0];
                for (int stap = 1; stap < array.length; stap++)
                    {
                        if (min.compareTo(array[stap]) == 1)
                            {
                                min = array[stap];
                            }
                    }
                return min;
            }


        public static <T> T min(final T[] array, final Comparator<? super T> comparator)
            {

                T min = array[0];
                for (int stap = 1; stap < array.length; stap++)
                    {
                        if (compare(min, array[stap], comparator) == 1)
                            {
                                min = array[stap];
                            }
                    }
                return min;
            }

        public static <T> T median(final T[] array, final Comparator<? super T> comparator)
            {
                int middle = (array.length - 1) / 2;
                for (int stap = (array.length - 1); stap > 0; stap--)
                    {
                        for (int byStap = 0; byStap < stap; byStap++)
                            {
                                if (compare(array[byStap], array[byStap + 1],comparator) == 1)
                                    {
                                        T tmp = array[byStap];
                                        array[byStap] = array[byStap + 1];
                                        array[byStap + 1] = tmp;
                                    }
                            }
                    }
                return array[middle];
            }


        public static <T extends Comparable> T median(final T[] array)
            {
                int middle = (array.length - 1) / 2;
                for (int stap = (array.length - 1); stap > 0; stap--)
                    {
                        for (int byStap = 0; byStap < stap; byStap++)
                            {
                                if (array[byStap].compareTo(array[byStap + 1]) == 1)
                                    {
                                        T tmp = array[byStap];
                                        array[byStap] = array[byStap + 1];
                                        array[byStap + 1] = tmp;
                                    }
                            }
                    }
                return array[middle];

            }
    }









