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

    boolean ignore() default false;
    Class<?> expected() default Exception.class;


}
