package javase03.common.exception;

/**
 * IO exception 
 * @author Yury.Vislobodsky
 *
 */
public class MyIOException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public MyIOException() {
		message = "I/O something wrong";
	}
	
	public MyIOException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}	
}

