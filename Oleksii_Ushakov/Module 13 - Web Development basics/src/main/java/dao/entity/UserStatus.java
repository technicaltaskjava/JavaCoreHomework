package dao.entity;

/**
 * @author augustprime
 */
public enum UserStatus {
    UNKNOWN, REGISTERED;

    @Override
    public String toString() {
        if (this == UserStatus.REGISTERED) {
            return "REGISTERED";
        } else {
            return "UNKNOWN";
        }
    }

    public static UserStatus fromString(String status) {
        if ("REGISTERED".equalsIgnoreCase(status)) {
            return REGISTERED;
        }
        return UNKNOWN;
    }
}
