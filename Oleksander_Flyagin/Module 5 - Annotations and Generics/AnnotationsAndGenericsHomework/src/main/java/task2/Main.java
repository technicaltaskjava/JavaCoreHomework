package task2;


import task2.MyComporator.MyIntegerComparator;
import task2.MyComporator.MyStringComparator;

public class Main
    {
        public static void main(String[] args)
            {
                MyIntegerComparator comparatoInteger = new  MyIntegerComparator();
                MyStringComparator comparatorString = new MyStringComparator();
                 String [] str = {"bbb", "ccc", "dddd", "aa"};
                Integer [] ar =  {1,2,13,12,7,5,10,8,9,10};



                System.out.println("MAX = "         + Task2a.max( ar));
                System.out.println("MAX = "         + Task2a.max(ar , comparatoInteger));
                System.out.println("MaxString = "   + Task2a.max(str));
                System.out.println("MaxString = "   + Task2a.max(str, comparatorString));

                System.out.println("MIN = "         + Task2a.min(ar));
                System.out.println("MIN = "         + Task2a.min(ar, comparatoInteger));
                System.out.println("MinString = "   + Task2a.min(str));
                System.out.println("MinString = "   + Task2a.min(str, comparatorString));


                System.out.println("MEDIAN = "       + Task2a.median(ar));
                System.out.println("MEDIAN = "       + Task2a.median(ar, comparatoInteger));
                System.out.println("MedianString = " + Task2a.median(str));
                System.out.println("MedianString = " + Task2a.median(str, comparatorString));











            }

    }
