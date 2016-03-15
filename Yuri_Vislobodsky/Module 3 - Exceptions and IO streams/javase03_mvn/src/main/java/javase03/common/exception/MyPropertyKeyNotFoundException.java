package javase03.common.exception;

/**
 * "Property key not found" exception 
 * @author Yury.Vislobodsky
 *
 */
public class MyPropertyKeyNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String propertyName;
		
	public MyPropertyKeyNotFoundException(String propertyName) {
		this.propertyName = propertyName;
	}
		
	public String toString() {
		return "Property key " + propertyName + " not found";
	}
}
