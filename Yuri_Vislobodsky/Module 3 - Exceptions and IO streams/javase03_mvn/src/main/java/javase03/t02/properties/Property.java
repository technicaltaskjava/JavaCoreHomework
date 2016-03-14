package javase03.t02.properties;

/**
 * Property class 
 * @author Yury.Vislobodsky
 *
 */
public class Property {
	private String name;
	private String value;
	
	public Property(String name, String value) {
		setName(name);
		setValue(value);
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
