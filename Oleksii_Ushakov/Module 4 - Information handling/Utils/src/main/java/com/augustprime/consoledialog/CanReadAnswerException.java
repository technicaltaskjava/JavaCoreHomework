package com.augustprime.consoledialog;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class CanReadAnswerException extends IOException {
    private static final String DEFAULT_MESSAGE = "Can`t read user answer";

    public CanReadAnswerException() {
        super(DEFAULT_MESSAGE);
    }
}
