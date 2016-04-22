package transaction;

import org.apache.log4j.Logger;

/**
 * @author Alexey Ushakov
 */
public class TransactionGroup extends ThreadGroup {
    private static final Logger console = Logger.getLogger("console");
    private static final Logger logger = Logger.getLogger(TransactionGroup.class);
    private boolean error = false;

    public TransactionGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        error = true;
        console.error(e.getMessage());
        logger.error(e.toString());
    }

    public boolean isError() {
        return error;
    }
}
