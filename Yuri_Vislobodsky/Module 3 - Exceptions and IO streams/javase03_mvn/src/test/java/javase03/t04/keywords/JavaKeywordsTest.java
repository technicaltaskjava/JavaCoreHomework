package javase03.t04.keywords;

import junit.framework.Test;
import junit.framework.TestCase;

public class JavaKeywordsTest extends TestCase {
	public void testJavaKeywords() throws Exception {
		String text = "public class JavaKeywordsTest extends TestCase";
		JavaKeywords javaKeywords = new JavaKeywords();
		String[] result = javaKeywords.getResult(text).split("\r\n");

		assertEquals("public - 1", result[0]);
		assertEquals("class - 1", result[2]);
		assertEquals("extends - 1", result[1]);
		assertEquals(3, result.length);
	}
}