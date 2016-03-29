package com.epam.task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Olga Kramska on 26-Mar-16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    boolean ignore() default false;
    Class<? extends Throwable> expected() default NoneException.class;

    public static class NoneException extends Throwable {
        private NoneException() {
        }
    }
}
