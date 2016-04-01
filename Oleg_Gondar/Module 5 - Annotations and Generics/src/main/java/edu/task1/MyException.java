package edu.task1;

/**
 * Created by Oleg on 14.03.2016.
 */
public class MyException extends Exception {

    final String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
