package t01.model;

import t01.exception.RunnerException;
import t01.model.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Runner {
	private static final String SEPARATOR = "=====================================================================";
	private boolean isPrintTestClass;
	private StringBuilder builder = null;

	public String run(String clazz) throws RunnerException {
		Object object = getObjectInstance(clazz);

		isPrintTestClass = false;
		Method[] testMethods = object.getClass().getDeclaredMethods();
		builder = new StringBuilder();
		for (Method method : testMethods) {
			invokeMethod(object, method);
		}
		printClassTest(object, "Finish", true);
		return builder != null ? builder.toString() : "Test methods not found";
	}

	private void invokeMethod(final Object object, final Method method) throws RunnerException {
		if (method.isAnnotationPresent(Test.class)) {
			printClassTest(object, "Start", false);
			isPrintTestClass = true;
			Test annotation = method.getDeclaredAnnotation(Test.class);
			boolean ignore = annotation.ignore();
			if (!ignore) {
				Class<? extends Throwable> expected = annotation.expected();
				try {
					method.invoke(object, null);
					if (expected != Test.None.class) {
						throw new InvocationTargetException(new AssertionError(String.format("Expected %s", expected)));
					}
					printResult(method, Status.PASSED);
				} catch (InvocationTargetException e) {
					exceptionProcessing(method, expected, e);
				} catch (IllegalAccessException e) {
					throw new RunnerException(e.getMessage());
				}
			} else {
				printResult(method, Status.IGNORED);
			}
		}
	}

	private void printClassTest(final Object object, final String status, final boolean expected) {
		if (isPrintTestClass == expected) {
			builder.append(SEPARATOR).append("\n");
			builder.append(String.format("\t%s run(-s) for class '%s'", status, object.getClass().getName())).append("\n");
			builder.append(SEPARATOR).append("\n");
		}
	}

	private Object getObjectInstance(final String clazz) throws RunnerException {
		Object object;
		try {
			Class<?> testClass = getClass().getClassLoader().loadClass(clazz);
			object = testClass.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new RunnerException(e.getMessage());
		}
		return object;
	}

	private void exceptionProcessing(final Method method, final Class<? extends Throwable> expected, final InvocationTargetException e) {
		Throwable cause = e.getCause();
		if (cause.getClass().equals(expected)) {
			printResult(method, Status.PASSED);
		} else {
			printResult(method, Status.FAILED, cause);
		}
	}

	private void printResult(final Method testMethod, final Status status) {
		printResult(testMethod, status, null);
	}

	private void printResult(final Method testMethod, final Status status, final Throwable throwable) {
		builder.append(SEPARATOR).append("\n");
		builder.append(String.format("Test method '%s' is %s", testMethod.getName(), status.getMessage())).append("\n");
		if (throwable != null) {
			builder.append(String.format("\tBecause:\n%s", throwable.getMessage())).append("\n");
		}
		builder.append(SEPARATOR).append("\n");
	}
}
