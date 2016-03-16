package task2;

import java.io.File;

public class Main
    {
        public static final String PROPERTIS_NAME = "src/Main/java/task2/config.properties";
        public static void main(String[] args)
            {
                Task2 task2 = new Task2(PROPERTIS_NAME);
                task2.getProperties();
                System.out.println();
                task2.showInfoProperties();
            }

    }
