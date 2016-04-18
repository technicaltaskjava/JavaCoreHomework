package account;

import org.apache.log4j.Logger;

/**
 * @author Alexey Ushakov
 */
public class AccountNumberException extends IllegalArgumentException {
    public static final Logger logger = Logger.getLogger("console");

    public AccountNumberException(String message) {
        super(message);
//        logger.error(message);
    }
}
