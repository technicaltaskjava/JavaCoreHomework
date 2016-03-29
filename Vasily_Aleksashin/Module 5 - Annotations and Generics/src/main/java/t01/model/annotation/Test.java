package t01.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	boolean ignore() default false;

	Class<? extends Throwable> expected() default None.class;

	class None extends Throwable {
		private None() {
		}
	}
}
