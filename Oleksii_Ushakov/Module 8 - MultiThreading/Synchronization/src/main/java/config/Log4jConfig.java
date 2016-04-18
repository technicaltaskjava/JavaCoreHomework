package config;

import java.io.File;
import java.util.Properties;

/**
 * @author Alexey Ushakov
 */
public class Log4jConfig {
    private static File logFile = new File("transaction.log");

    public static Properties configProperties() {
        Properties properties = new Properties();
        properties.setProperty("log4j.rootLogger", "INFO, file");
        properties.setProperty("log4j.logger.console", "ERROR, console");

        properties.setProperty("log4j.appender.file", "org.apache.log4j.RollingFileAppender");
        properties.setProperty("log4j.appender.file.File", logFile.getAbsolutePath());
        properties.setProperty("log4j.appender.file.MaxFileSize", "3MB");
        properties.setProperty("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
        properties.setProperty("log4j.appender.file.layout.ConversionPattern", "%p: %m%n");

        properties.setProperty("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
        properties.setProperty("log4j.appender.console.target", "System.out");
        properties.setProperty("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
        properties.setProperty("log4j.appender.console.layout.ConversionPattern", "%m%n");

        return properties;
    }

    public static File getLogFile() {
        return logFile;
    }
}
