package com.task1;
import com.task1.application.Application;


/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) throws  InterruptedException {

        Application application = new Application();
        application.run();

    }
}
