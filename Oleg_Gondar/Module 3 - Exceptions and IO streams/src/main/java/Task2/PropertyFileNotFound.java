package Task2;

/**
 * Created by Oleg on 15.03.2016.
 */
public class PropertyFileNotFound extends Exception {

    String notFoundedFile;

    public PropertyFileNotFound(String message, String notFoundedFile) {
        super(message);
        this.notFoundedFile = notFoundedFile;
    }

    @Override
    public String toString() {
        return "PropertyFileNotFound{" +
                "notFoundedFile='" + notFoundedFile + '\'' +
                '}';
    }
}
