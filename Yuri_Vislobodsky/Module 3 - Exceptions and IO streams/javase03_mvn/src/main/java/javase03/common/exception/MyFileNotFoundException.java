package javase03.common.exception;

/**
 * "File not found" exception 
 * @author Yury.Vislobodsky
 *
 */
public class MyFileNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String fileName;
	
	public MyFileNotFoundException(String fileName) {
		this.fileName = fileName;
	}
	
	public String toString() {
		return "File " + fileName + " not found";
	}
}
