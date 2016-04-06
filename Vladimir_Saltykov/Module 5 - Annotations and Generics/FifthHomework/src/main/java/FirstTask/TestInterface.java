package FirstTask;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)

    public @interface TestInterface {
        boolean ignore() default false;
        Class<? extends Throwable> expected() default NoneException.class;

        public static class NoneException extends Throwable {
            private NoneException() {
            }
        }
    }

