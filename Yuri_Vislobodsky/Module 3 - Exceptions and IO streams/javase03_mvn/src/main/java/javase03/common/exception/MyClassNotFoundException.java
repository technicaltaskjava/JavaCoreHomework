package javase03.common.exception;

/**
 * "Class not found" exception 
 * @author Yury.Vislobodsky
 *
 */
public class MyClassNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String className;
	
	public MyClassNotFoundException(String className) {
		this.className = className;
	}
	
	public String toString() {
		return "Class " + className + " not found in this app";
	}
}
