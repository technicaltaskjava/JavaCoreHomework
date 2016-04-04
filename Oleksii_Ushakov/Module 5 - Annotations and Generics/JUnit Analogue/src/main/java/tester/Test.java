package tester;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alexey Ushakov
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    class NotThrow extends Throwable {

    }

    boolean ignore() default false;

    Class<? extends Throwable> expected() default NotThrow.class;
}
