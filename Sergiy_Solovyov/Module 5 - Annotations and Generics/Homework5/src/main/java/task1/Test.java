package task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

    boolean ignore() default false;

    Class<? extends Exception> expected() default Test.Default.class;

    public class Default extends Exception {
        private Default() {
        }
    }

}