package transaction.parser.util;

/**
 * @author Alexey Ushakov
 */
public enum TransactionXMLTag {
    NOT_TAG, TRANSACTION, SENDER, RECIPIENT, VALUE;

    public static TransactionXMLTag getTag(String name) {
        try {
            return TransactionXMLTag.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NOT_TAG;
        }
    }
}
