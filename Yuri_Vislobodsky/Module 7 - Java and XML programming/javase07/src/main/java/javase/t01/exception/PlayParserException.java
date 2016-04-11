package javase.t01.exception;

/**
 * Common exception raising when some troubles take place with Play Parser
 * Created by Yury Vislobodsky on 10.04.2016.
 */
public class PlayParserException extends Exception {
    private final Exception exception;

    public PlayParserException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Play Parser exception: " + exception.getMessage();
    }
}

