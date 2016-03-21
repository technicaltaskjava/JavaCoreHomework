package consoledialog;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class CanReadAnswer extends IOException {
    private static final String DEFAULT_MESSAGE = "Can`t read user answer";

    public CanReadAnswer() {
        super(DEFAULT_MESSAGE);
    }
}
