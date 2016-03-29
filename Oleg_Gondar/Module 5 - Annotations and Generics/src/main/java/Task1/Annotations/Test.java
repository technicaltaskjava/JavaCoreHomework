package Task1.Annotations;

import Task1.MyException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Oleg on 27.03.2016.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)


public @interface Test {

    static class None extends Throwable {
        private static final long serialVersionUID = 1L;

        private None() {
        }
    }

    boolean ignore() default false;
    Class<?> expected() default None.class;

}

