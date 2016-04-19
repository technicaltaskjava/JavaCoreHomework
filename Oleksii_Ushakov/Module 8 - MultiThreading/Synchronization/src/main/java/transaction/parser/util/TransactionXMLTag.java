package transaction.parser.util;

/**
 * @author Alexey Ushakov
 */
public enum TransactionXMLTag {
    NOT_TAG, TRANSACTION, SENDER, RECIPIENT, VALUE;

    public static TransactionXMLTag getTag(String name) {

        for (int i = 0; i < TransactionXMLTag.values().length; ) {
            if (name.equalsIgnoreCase(TransactionXMLTag.values()[i].toString())) {
                return TransactionXMLTag.values()[i];
            }
        }

        return NOT_TAG;
    }
}
