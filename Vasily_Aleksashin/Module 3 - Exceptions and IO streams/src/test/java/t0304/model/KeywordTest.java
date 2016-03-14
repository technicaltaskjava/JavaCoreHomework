package t0304.model;

import org.junit.BeforeClass;
import org.junit.Test;
import t01.exception.ModelException;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class KeywordTest {
	private static final String PATHNAME = "src/main/resources/java_keywords.txt";
	private static final int INDEX_KEYWORDS = 0;
	private static Keyword keywords;

	@BeforeClass
	public static void setUp() throws FileNotFoundException, ModelException {
		keywords = new Keyword(PATHNAME);
	}

	@Test
	public void testFindKeywordSingle() throws ModelException {
		String keyword = "abstract";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 1);
	}

	@Test
	public void testFindKeywordTwiceWithSpace() throws ModelException {
		String keyword = "abstract abstract";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 2);
	}

	@Test
	public void testFindKeywordWithoutSeparatorsBefore() throws ModelException {
		String keyword = "Wabstract";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 0);
	}

	@Test
	public void testFindKeywordWithoutSeparatorsAfter() throws ModelException {
		String keyword = "abstractW";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 0);
	}

	@Test
	public void testFindKeywordWithoutSeparatorsBeforeAndAfter() throws ModelException {
		String keyword = "WabstractW";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 0);
	}

	@Test
	public void testFindKeywordWithSeparatorsBefore() throws ModelException {
		String keyword = ".abstract";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 1);
	}

	@Test
	public void testFindKeywordWithSeparatorsAfter() throws ModelException {
		String keyword = "abstract(";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 1);
	}

	@Test
	public void testFindKeywordWithSeparatorsBeforeAndAfter() throws ModelException {
		String keyword = "[abstract]";
		keywords.findKeyword(keyword, INDEX_KEYWORDS);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 1);
	}

	@Test
	public void testJavaCode() throws ModelException {
		int indexKeywords = 38;
		String textForTest = "import java.util.function.Predicate;\n" +
				"\n" +
				"public class BinarySearchSpan {\n" +
				"    // exists i:\n" +
				"    //      (forall j in (0..i) predicate.test(a[i]))\n" +
				"    //      && (forall j in [i, a.length): predicate.test(a[j]))\n" +
				"    public static int iterativeBinarySearch(int[] a, Predicate<Integer> predicate) {\n" +
				"        int l = -1, r = a.length;\n" +
				"\n" +
				"        // (l == -1 || !predicate.test(a[l])) && (r == a.length || predicate.test(a[r]))\n" +
				"        while (l < r - 1) {\n" +
				"            int mid = l + (r - l) / 2;\n" +
				"            if (predicate.test(a[mid])) {\n" +
				"                r = mid;\n" +
				"            }\n" +
				"            else {\n" +
				"                l = mid;\n" +
				"            }\n" +
				"        }\n" +
				"\n" +
				"        return r;\n" +
				"    }\n" +
				"    // if (exists i: predicate.test(a[i])) then\n" +
				"    //     result = min i: predicate.test(a[i])\n" +
				"    // else\n" +
				"    //     result = a.length\n" +
				"\n" +
				"\n" +
				"    public static void main(String[] args) {\n" +
				"        int a[] = new int[args.length - 1];\n" +
				"        for (int i = 1; i < args.length; ++i) {\n" +
				"            a[i - 1] = Integer.parseInt(args[i]);\n" +
				"        }\n" +
				"\n" +
				"        int x = Integer.parseInt(args[0]);\n" +
				"\n" +
				"        int i1 = iterativeBinarySearch(a, (a1) -> a1 <= x);\n" +
				"        int length = iterativeBinarySearch(a, (a1) -> a1 < x) - i1;\n" +
				"\n" +
				"        System.out.println(i1 + \" \" + length);\n" +
				"    }\n" +
				"}";
		keywords.findKeyword(textForTest, indexKeywords);
		assertEquals(2, keywords.getKeywordCount()[indexKeywords]);
	}

	@Test
	public void testFindAllKeywords() throws ModelException {
		String keyword = "[abstract]";
		keywords.findAllKeyword(keyword);
		assertTrue(keywords.getKeywordCount()[INDEX_KEYWORDS] == 1);
	}

	@Test(expected = ModelException.class)
	public void testFindWithNull() throws ModelException {
		keywords.findAllKeyword(null);

	}

	@Test(expected = ModelException.class)
	public void testFindKeywordWithWrongIndex() throws ModelException {
		keywords.findKeyword("", Integer.MAX_VALUE);

	}

	@Test
	public void testGetKeywordArray() {
		assertNotNull(keywords.getKeywordArray());

	}

	@Test
	public void testToString() {
		String expected = "Keyword{\n" +
				"abstract = 0\n" +
				"continue = 0\n" +
				"for = 0\n" +
				"new = 0\n" +
				"switch = 0\n" +
				"assert = 0\n" +
				"default = 0\n" +
				"goto = 0\n" +
				"package = 0\n" +
				"synchronized = 0\n" +
				"boolean = 0\n" +
				"do = 0\n" +
				"if = 0\n" +
				"private = 0\n" +
				"this = 0\n" +
				"break = 0\n" +
				"double = 0\n" +
				"implements = 0\n" +
				"protected = 0\n" +
				"throw = 0\n" +
				"byte = 0\n" +
				"else = 0\n" +
				"import = 0\n" +
				"public = 0\n" +
				"throws = 0\n" +
				"case = 0\n" +
				"enum = 0\n" +
				"instanceof = 0\n" +
				"return = 0\n" +
				"transient = 0\n" +
				"catch = 0\n" +
				"extends = 0\n" +
				"int = 0\n" +
				"short = 0\n" +
				"try = 0\n" +
				"char = 0\n" +
				"final = 0\n" +
				"interface = 0\n" +
				"static = 0\n" +
				"void = 0\n" +
				"class = 0\n" +
				"finally = 0\n" +
				"long = 0\n" +
				"strictfp = 0\n" +
				"volatile = 0\n" +
				"const = 0\n" +
				"float = 0\n" +
				"native = 0\n" +
				"super = 0\n" +
				"while = 0\n" +
				"}";
		String actual = keywords.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = FileNotFoundException.class)
	public void testInitWithWrongFile() throws FileNotFoundException, ModelException {
		new Keyword("");

	}
}