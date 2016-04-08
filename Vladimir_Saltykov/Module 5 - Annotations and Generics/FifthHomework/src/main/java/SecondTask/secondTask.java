package SecondTask;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class secondTask  {

static int size = setSize();

    public static void main(String[] args) {

        min();
        median();
        max();

    }

    public static Object[] min(){
        Object[] minValue = new Object[size];
        System.arraycopy(sorted(),0, minValue, 0, 1 );
        System.out.println("Min Value = " + minValue[0]);
        return minValue;
    }

    public static Object[] max (){
        Object[] maxValue = new Object[size];
        System.arraycopy(sorted(),size-1, maxValue, 0, 1 );
        System.out.println("Max Value = " + maxValue[0]);
        return maxValue ;
    }

    public static Object[] median(){

        Object[] midValue = new Object[size];
        System.arraycopy(sorted(),size/2, midValue, 0, 1 );
        System.out.println("Median Value = " + midValue[0]);
        return midValue ;
    }

    public  static MultiClass createArray(){

        MultiClass multiClass = new MultiClass();
        multiClass.getArray(size);
        for (int i = 0; i < size ; i++) {
            multiClass.setValue((int)(Math.random() * 5) - i);
        }
       return multiClass;
    }

    public static Object sorted() {

       Object[] array = new Object[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = createArray().getValue();
        }
        Arrays.sort(array);
        return array;
    }

    public static int setSize(){
        System.out.println("Please input how many Objects do you want to create?: ");
        Scanner sc = new Scanner(System.in);

        try {
           size  = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Please input only numbers, Try again");
           size = setSize();
        }
        return size;
    }
}
