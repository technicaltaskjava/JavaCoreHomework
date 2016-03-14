package javase03.t04.keywords;

/**
 * Java keywords class 
 * @author Yury.Vislobodsky
 *
 */
public class JavaKeywords {
	private final String[] keywords;
	private int[] count;
	
	public JavaKeywords() {
		keywords = new String[] {
				"abstract", "continue", "for",        "new",       "switch",
				"assert",   "default",  "goto",       "package",   "synchronized",
				"boolean",  "do",       "if",         "private",   "this",
				"break",    "double",   "implements", "protected", "throw",
				"byte",     "else",     "import",     "public",    "throws",
				"case",     "enum",     "instanceof", "return",    "transient",
				"catch",    "extends",  "int",        "short",     "try",
				"char",     "final",    "interface",  "static",    "void",
				"class",    "finally",  "long",       "strictfp",  "volatile",
				"const",    "float",    "native",     "super",     "while"
		};
		count = new int[length()];
		clearStatistics();
	}

	public int length() {
		return (keywords != null) ? keywords.length : 0;
	}
	
	public String getKeyword(int index) {
		return ((index >= 0) && (index < length())) ? keywords[index] : ""; 
	}

	public int getKeywordCount(int index) {
		return ((index >= 0) && (index < length())) ? count[index] : 0; 
	}	
	
	public void clearStatistics() {
		for (int index = 0; index < length(); index++) {
			count[index] = 0;
		}
	}
		
	private boolean isLetterOrDigitOrUnderline(char c) {
		return (((c >= 'A') && (c <= 'Z')) || 
				((c >= 'a') && (c <= 'z')) ||
				(c == '_')) ? true : false;
	}
	
	public String getResult(String text) {
		if (text == null) {
			text = "";
		}
		text = " " + text + " ";
		for (int index = 0; index < length(); index++) {
			String keyword = keywords[index];
			int keywordLength = keyword.length();
			int fromIndex = -1;
			while ((fromIndex = text.indexOf(keyword, fromIndex+1)) != -1) {
				if (!isLetterOrDigitOrUnderline(text.charAt(fromIndex + keywordLength)) &&
						!isLetterOrDigitOrUnderline(text.charAt(fromIndex - 1))) {
					count[index]++;
				}
			}
		}
		String outputText = "";
		for (int index = 0; index < length(); index++) {
			if (getKeywordCount(index) > 0) {
				outputText += getKeyword(index) + " - " + 
								getKeywordCount(index) + "\r\n";
			}
		}
		return outputText;
	}
}
