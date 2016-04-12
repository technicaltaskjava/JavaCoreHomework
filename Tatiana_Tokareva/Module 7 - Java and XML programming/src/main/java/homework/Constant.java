package homework;

import java.util.regex.Pattern;

public class Constant {
    public static final String MODEL_VERSION = "modelVersion";
    public static final String PACKAGING = "packaging";
    public static final String DEPENDENCIES = "dependencies";
    public static final String DEPENDENCY = "dependency";
    public static final String BUILD = "build";
    public static final String PLUGINS = "plugins";
    public static final String PLUGIN = "plugin";
    public static final String CONFIGURATION = "configuration";
    public static final String SOURCE = "source";
    public static final String TARGET = "target";
    public static final String JAVA_VERSION = "1.7";
    public static final String PROJECT = "project";
    public static final String URL_XML_FILE = "http://www.ibiblio.org/xml/examples/shakespeare/as_you.xml";
    public static final String SPEECH = "SPEECH";
    public static final String SPEAKER = "SPEAKER";
    public static final String LINE = "LINE";
    public static final String WILLIAM = "WILLIAM";
    public static final String GROUP_ID = "groupId";
    public static final String ARTIFACT_ID = "artifactId";
    public static final String VERSION = "version";
    public static final String PATHNAME = "src/main/resources/pom.xml";
    public static final Pattern PATTERN = Pattern.compile("[ ]");

    private Constant() {
    }

}
