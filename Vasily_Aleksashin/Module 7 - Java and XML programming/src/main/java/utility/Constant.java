package utility;

import java.util.regex.Pattern;

public class Constant {
	public static final String SEPARATOR = "=====================================================";
	public static final String REPLACEMENT = " ";
	public static final String REGEX_WHITESPACE = "([\n]+)|([ ]+)";
	public static final String LIST_OF_CHARACTERS_IS_EMPTY = "List of characters is EMPTY";
	public static final String ENTER_CHARACTER_NAME = "Enter character name:";
	public static final String CHARACTER_NOT_FOUND = "Character '%s' not found";
	public static final String ROOT_ELEMENT = "project";
	public static final String MODEL_VERSION = "modelVersion";
	public static final String MODEL_VERSION_DATA = "4.0.0";
	public static final String PACKAGING = "packaging";
	public static final String JAR = "jar";
	public static final String DEPENDENCIES = "dependencies";
	public static final String DEPENDENCY = "dependency";
	public static final String BUILD = "build";
	public static final String PLUGINS = "plugins";
	public static final String PLUGIN = "plugin";
	public static final String GROUP_ID = "groupId";
	public static final String ARTIFACT_ID = "artifactId";
	public static final String VERSION = "version";
	public static final String PATHNAME = "src/main/resources/custom_pom.xml";
	public static final String DEPEND_DATA_FILE = "src/main/resources/depend_data.txt";
	public static final String PLUGIN_DATA_FILE = "src/main/resources/plugin_data.txt";
	static final String DEST_PATH = "/xml/";
	static final String MAIN_URL = "http://www.ibiblio.org/xml/examples/shakespeare/";
	static final Pattern PATTERN_HREF = Pattern.compile("(<LI><a href=.*\">)");
	static final Pattern PATTERN_XML_FILENAME = Pattern.compile("(\".+\")");
	private static final String REGEX_SPACE = "[ ]";
	public static final Pattern PATTERN_SPACE = Pattern.compile(REGEX_SPACE);

	private Constant() {
	}
}
