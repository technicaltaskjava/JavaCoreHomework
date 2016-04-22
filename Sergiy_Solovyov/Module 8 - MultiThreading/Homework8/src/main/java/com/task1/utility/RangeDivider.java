package com.task1.utility;



import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Solovyov
 */
public class RangeDivider {

    private RangeDivider(){}

    public static List<Integer> divide(int start, int end, int threads){
        int range = end - start;
        ArrayList<Integer>arrayList = new ArrayList<>();
        int interval = range/threads;
        arrayList.add(start);
        int st = start;

        for (int i = 0; i < threads - 1; i++){
            arrayList.add(st + interval);
            st = st + interval + 1;
            arrayList.add(st);
        }

        arrayList.add(end);
        return arrayList;
    }
}
