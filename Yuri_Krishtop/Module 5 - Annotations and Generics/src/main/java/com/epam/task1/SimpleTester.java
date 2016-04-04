package com.epam.task1;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yuriy Krishtop on 24.03.2016.
 */
public class SimpleTester {

    private static final String CURRENT_DIR = "./src/main/java/";
    private int countTestMethods = 0;
    private int countSuccessfulTest = 0;
    private int countUnsuccessfulTest = 0;
    private String packageName;
    private String sourcesFolderName;

    public SimpleTester() {
        this("com.epam.task1.");
    }

    public SimpleTester(String packageName) {
        this.packageName = packageName;
        sourcesFolderName = CURRENT_DIR + packageName.replaceAll("\\.", "/");
        File dir = new File(sourcesFolderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("| Please, put files for testing in folder: " + sourcesFolderName + "                                  |");
    }

    public String[] findClassesForTest() {
        File dir = new File(sourcesFolderName);
        String[] classNames = null;
        if (dir.exists()) {
            if (dir.isDirectory()) {
                FilenameFilter testFilter = new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        if (name.endsWith("STest.java")) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                };
                File[] classes = dir.listFiles(testFilter);
                if (classes.length == 0) {
                    System.out.println("In current directory " + sourcesFolderName + " no files to test.");
                } else {
                    classNames = new String[classes.length];
                    for (int i = 0; i < classes.length; i++) {
                        classNames[i] = classes[i].getName().substring(0, classes[i].getName().length() - 5);
                    }
                }
            } else {
                System.out.println("Current selection " + sourcesFolderName + " is not directory.");
            }
        } else {
            System.out.println("Current directory " + sourcesFolderName + " does not exist.");
        }
        return classNames;
    }

    public void runTestMethod(String className) {
        try {
            String line = "+-----------------------------------------------------------------------------------------------------------+";
            Class classForTest = Class.forName(packageName + className);
            Method[] methods = classForTest.getDeclaredMethods();
            try {
                Object obj = classForTest.newInstance();
                for (Method m : methods) {
                    if (m.isAnnotationPresent(Test.class)) {
                        Test test = m.getAnnotation(Test.class);
                        boolean ignore = test.ignore();
                        Class expected = test.expected();
                        if (ignore) {
                            continue;
                        }
                        countTestMethods++;
                        System.out.println(line);
                        System.out.printf("%-60s", "| Testing class : " + className + ".java");
                        System.out.printf("%49s%n", "      |");
                        System.out.printf("%-60s", "| Testing method : " + m.getName());
                        System.out.printf("%49s%n", "      |");
                        try {
                            m.invoke(obj);
                            countSuccessfulTest++;
                            System.out.printf("%-60s", "| Successful.");
                            System.out.printf("%49s%n", "      |");
                        } catch (InvocationTargetException e) {
                            System.out.printf("%-60s", "| Exception : " + e.getCause().getClass());
                            System.out.printf("%49s%n", "      |");
                            System.out.printf("%-80s", "| Extended exception : " + expected);
                            System.out.printf("%29s%n", "      |");
                            if (e.getCause().getClass() == expected) {
                                countSuccessfulTest++;
                                System.out.printf("%-80s", "| Successful.");
                                System.out.printf("%29s%n", "      |");
                            } else {
                                countUnsuccessfulTest++;
                                System.out.printf("%-80s", "| Unsuccessful.");
                                System.out.printf("%29s%n", "      |");
                            }
                        }
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getCountTestMethods() {
        return countTestMethods;
    }

    public int getCountSuccessfulTest() {
        return countSuccessfulTest;
    }

    public int getCountUnsuccessfulTest() {
        return countUnsuccessfulTest;
    }
}
