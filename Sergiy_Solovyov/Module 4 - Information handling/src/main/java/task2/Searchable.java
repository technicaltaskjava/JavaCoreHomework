package task2;

import java.io.ByteArrayOutputStream;
/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public interface Searchable {

    void logsByLevel(Level level);
    void logsByDate(int day, int month, int year) throws WrongDateException;

}
