package annotations;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    boolean ignore() default false;

    //Is there any way to have a field without default value and without explicitly assigning a value to it?
    //Here IndexOutOfBoundsException is used because it will never be thrown in current example, so it doesn't affect
    //anything, but i realize this is a bad way to do it. How do i do it properly?
    Class expected() default IndexOutOfBoundsException.class;
}
