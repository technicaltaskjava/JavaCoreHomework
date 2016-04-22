package com.epam.taskMultithreading;


import java.util.ArrayList;
import java.util.List;

public class NuberSercherTread extends Thread
    {
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
        public void run()
            {
                int divisor = 0;
                for (int i = begin; i < last; i++)
                    {
                        for (int j = 1; j <= i; j++)
                            {
                                if (i % j == 0)
                                    {
                                        divisor++;
                                    }
                                if (divisor > 2)
                                    {
                                        divisor = 0;
                                    }
                            }
                        if (divisor == 2)
                            {
                                nuberList.add(i);

                            }
                        divisor = 0;
                    }

            }
    }
