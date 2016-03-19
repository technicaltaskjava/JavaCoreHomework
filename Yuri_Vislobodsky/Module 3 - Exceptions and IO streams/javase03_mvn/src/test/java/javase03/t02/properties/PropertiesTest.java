package javase03.t02.properties;

import junit.framework.Test;
import junit.framework.TestCase;

public class PropertiesTest extends TestCase {
	public void testProperties() throws Exception {
		Properties properties = new Properties("Param_1=35\r\nParam_2=UserName098\r\nParam_3=I like Java:)");
		assertEquals("35", properties.getPropertyValue("Param_1"));
		assertEquals("UserName098", properties.getPropertyValue("Param_2"));
		assertEquals("I like Java:)", properties.getPropertyValue("Param_3"));
		assertEquals(3, properties.length());
	}
}