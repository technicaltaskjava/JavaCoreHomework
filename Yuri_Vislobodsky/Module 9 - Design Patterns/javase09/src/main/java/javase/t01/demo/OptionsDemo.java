package javase.t01.demo;

import javase.t01.options.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo class to show results of applying the Singleton pattern.
 * Get a key value from file, increment it and then write to file
 * Created by Yury on 22.04.2016.
 */
public class OptionsDemo {
    private static Logger logger = LoggerFactory.getLogger(OptionsDemo.class);
    public static final String OPTIONS_FILE_NAME = "src/main/resources/options.properties";
    public static final String PARAMETER = "parameter";

    private OptionsDemo() {}

    public static int nextValue() {
        Options options = Options.getInstance();
        options.loadFromFile(OptionsDemo.OPTIONS_FILE_NAME);
        int parameter = Integer.parseInt(options.getOption(OptionsDemo.PARAMETER, "0"));
        parameter++;
        options.setOption(OptionsDemo.PARAMETER, String.valueOf(parameter));
        options.saveToFile(OptionsDemo.OPTIONS_FILE_NAME);
        return parameter;
    }

    public static void main(String[] args) {
        logger.info("Parameter value   : " + nextValue());
        logger.info("Incremented value : " + nextValue());
    }
}
