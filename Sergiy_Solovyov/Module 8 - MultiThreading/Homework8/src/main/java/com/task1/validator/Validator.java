package com.task1.validator;

import com.task1.exceptions.WrongArgumentException;

/**
 * @author Sergey Solovyov
 */
public class Validator {

    private Validator(){}

    public static void validate(int start, int end, int treads) throws WrongArgumentException {
        if (start < 1)
            throw new WrongArgumentException("Start number must be bigger than 1");
        if (end < 10 || end > Integer.MAX_VALUE)
            throw new WrongArgumentException("End number is from 10 to " + Integer.MAX_VALUE);
        if ((end - start)/treads < 2)
            throw new WrongArgumentException("You want to use to much thread for this range");
    }
}
