package javase05.t01.annotation;

import java.lang.annotation.*;

/**
 * Definition of Test annotation
 * Created by Yury Vislobodsky on 23.03.2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    boolean ignore() default false;
    Class expected() default Object.class;
}
