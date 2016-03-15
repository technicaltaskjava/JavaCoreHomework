package Task2;

/**
 * Created by Oleg on 14.03.2016.
 */
public class KeyNotFoundInPropertyFileException extends Exception {

    String notFindedKey;

    KeyNotFoundInPropertyFileException(String key) {
        notFindedKey = key;
    }

    @Override
    public String toString() {
        return "KeyNotFoundInPropertyFileException{" +
                "notFindedKey='" + notFindedKey + '\'' +
                '}';
    }
}
