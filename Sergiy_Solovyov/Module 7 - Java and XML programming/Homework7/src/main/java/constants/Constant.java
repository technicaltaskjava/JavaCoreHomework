package constants;

import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 */
public class Constant {

    public static final String PROJECT = "project";
    public static final String GROUP_ID = "groupId";
    public static final String ARTIFACT_ID = "artifactId";
    public static final String PROPERTIES = "properties";
    public static final String SOURCE_ENCODING = "project.build.sourceEncoding";
    public static final String DEPENDENCIES = "dependencies";
    public static final String DEPENDENCY = "dependency";
    public static final String MODEL_VERSION = "modelVersion";
    public static final String VERSION = "version";
    public static final String PLUGIN = "plugin";
    public static final String PLUGINS = "plugins";
    public static final String BUILD = "build";
    public static final String SCOPE = "scope";
    public static final String CONFIGURATION = "configuration";
    public static final String SOURCE = "source";
    public static final String TARGET = "target";
    public static final String SPEAKER = "SPEAKER";
    public static final String SPEECH = "SPEECH";
    public static final String LINE = "LINE";
    public static final String STAGE_DIR = "STAGEDIR";
    public static final String XMLNS = "xmlns";
    public static final String XMLNS_XSI = "xmlns:xsi";
    public static final String XMLNS_SCHEMA_LOCATION = "xsi:schemaLocation";
    public static final String XMLNS_VALUE = "http://maven.apache.org/POM/4.0.0";
    public static final String XMLNS_XSI_VALUE = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String XMLNS_SCHEMA_LOCATION_VALUE = "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd";
    public static final Pattern PATTERN = Pattern.compile("\\w+");

    private Constant(){}


}
