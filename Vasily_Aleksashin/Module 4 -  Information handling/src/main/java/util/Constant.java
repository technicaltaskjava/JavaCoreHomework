package util;

import java.util.regex.Pattern;

public class Constant {
	public static final String NO_RESULT = "no result";
	public static final String NO_MESSAGE = "no message";
	public static final String SEPARATOR = "==========================================================================";
	public static final String ENTER_MENU_NUMBER = "Enter menu number:";
	public static final String DEFAULT_TEXT_FILE = "4. Information Handling Programmers Book.txt";
	public static final String DEFAULT_HTML_FILE = "4. Information handling_task_attachment.html";

	public static final String SPACE = " ";
	public static final String DATE_FORMAT = "dd-MM-yyyy : HH-mm";
	public static final String CONSONANT = "qwrtpsdfghjklzxcvbnmQWRTPSDFGHJKLZXCVBNMцкнгшщзхфвпрлджчсмтбЦКНГШЩЗХФВПРЛДЖЧСМТБ";
	public static final String VOWELS = "[eyuioaEYUIOAуеыаоэяиюУЕЫАОЭЯИЮ]";
	public static final String NORMALIZE = "[ \\t]+";
	public static final String WORD_LENGTH = "\\b([%s]\\p{L}{%s})\\b";
	public static final String WORD_FIRST_LATTER = "(([а-яА-яa-zA-z\\d])([а-яА-яa-zA-z-[^\\s\\D]]+))";
	public static final String WORD = "([а-яА-яa-zA-z-[^\\s\\D]]+)";
	public static final String CLAUSE_DELIMITER = "([\\.?!\\n][ \\n]*[\\nА-Я])";
	public static final String WORD_DELIMITER = "([,=+\"/()\\[\\]\\\\'—-]?[ ][,=+\"/()\\[\\]\\\\'—-]?)";
	public static final String BLOCK_DELIMITER = "((<[/]?div>)|(<[/]?p>))";
	public static final String INNER_BLOCK_DELIMITER = "((<[/]?\\w+>)|(&[\\w\\d]+;))";
	public static final String IMAGE_LINK = "([(][Рр]ис. \\d)";
	public static final String UNNECESSARY_SPACE = "\\n[ ]+";
	public static final String CLAUSE_ENDING = ".*[\\.!?][ ]?";
	public static final String MESSAGE_FORMAT = "((\\d{2}-\\d{2}-\\d{4} : \\d{2}-\\d{2}) - (.*[^\\n]))";

	public static final Pattern PATTERN_VOWELS = Pattern.compile(VOWELS);
	public static final Pattern PATTERN_WORD_FIRST_LATTER = Pattern.compile(WORD_FIRST_LATTER);
	public static final Pattern PATTERN_WORD = Pattern.compile(WORD);
	public static final Pattern PATTERN_CLAUSE_DELIMITER = Pattern.compile(CLAUSE_DELIMITER);
	public static final Pattern PATTERN_WORD_DELIMITER = Pattern.compile(WORD_DELIMITER);
	public static final Pattern PATTERN_BLOCK_DELIMITER = Pattern.compile(BLOCK_DELIMITER);
	public static final Pattern PATTERN_INNER_BLOCK_DELIMITER = Pattern.compile(INNER_BLOCK_DELIMITER);
	public static final Pattern PATTERN_IMAGE_LINK = Pattern.compile(IMAGE_LINK);
	public static final Pattern PATTERN_MESSAGE_FORMAT = Pattern.compile(MESSAGE_FORMAT);
}
