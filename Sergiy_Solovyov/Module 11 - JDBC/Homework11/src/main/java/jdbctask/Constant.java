package jdbctask;

/**
 * @author Sergey Solovyov
 */
public class Constant {

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSW = "password";
    public static final String EMAIL = "email";
    public static final String AGE = "age";
    public static final String COOKIE = "cookie";
    public static final String COOKIE_ID = "cookie_id";
    public static final String USER_ID = "user_id";
    public static final String TIME_ADDED = "time_added";
    public static final String UPDATE_SQL = "UPDATE USERS SET USERNAME = ? "
            + " WHERE ID = ?";
    public static final String SELECT_BY_ID = "SELECT * from USERS WHERE id = (?)";
    public static final String SELECT_ALL_USERS = "SELECT * from USERS";
    public static final String SELECT_ALL_METADATA = "SELECT * from METADATA";
    public static final String SELECT_BY_AGE = "SELECT * FROM USERS WHERE age BETWEEN (?) and (?) ORDER BY id";
    public static final String INSERT_USER = "INSERT INTO USERS"
            + "(`username`, `email`, `password`, `age`) VALUES"
            + "(?,?,?,?)";
    public static final String DELETE_METADATA_BY_ID = "DELETE METADATA WHERE ID = ?";
    public static final String SEPARATOR = "-----------------------------------------";

    private Constant(){}
}
