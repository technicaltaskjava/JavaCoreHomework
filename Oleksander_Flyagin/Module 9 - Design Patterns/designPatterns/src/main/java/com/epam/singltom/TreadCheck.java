package com.epam.singltom;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TreadCheck extends Thread {
    private  MySingleton logger = null;
    private int begin;
    private int last;
    private   List nuberList = new ArrayList();

    public void setBegin(int begin)
    {
        this.begin = begin;
    }

    public void setLast(int last)
    {
        this.last = last;
    }

    public List getNuberList()
    {
        return nuberList;
    }

    @Override
    public void run() {

        for (int start = begin; start < last; start++ ) {
            if (start % 2 == 0)
                nuberList.add(start);
        }
        logger = MySingleton.getInstance();
        logger.getLog(this.getNuberList(), this.getName());

    }
}
