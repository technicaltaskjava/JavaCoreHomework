package javase.dao.datasource;

import javase.dao.transfer.User;
import java.sql.SQLException;

/**
 * Abstract interface for table USERS
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface UsersDAO {
    int insert(User user) throws SQLException;
    User getUser(String email, String password) throws SQLException;
    User getUser(String email) throws SQLException;
    void closeResultSet() throws SQLException;
}
