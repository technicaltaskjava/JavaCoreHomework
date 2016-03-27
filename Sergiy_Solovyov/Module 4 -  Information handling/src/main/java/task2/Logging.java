package task2;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public interface Logging {

    void info(String message);
    void conf(String message);
    void warn(String message);
    void server(String message);
    void log(String message, Throwable ex);

}
