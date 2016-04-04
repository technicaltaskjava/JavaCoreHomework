package com.kokhanyuk.javase05.myjunit.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test
 *
 *This class describes the annotation for testing.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    boolean ignore() default false;
    Class expected() default Null.class;
}