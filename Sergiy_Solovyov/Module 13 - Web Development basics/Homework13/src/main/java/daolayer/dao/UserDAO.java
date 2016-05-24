package daolayer.dao;

import daolayer.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sergey Solovyov
 */
public interface UserDAO {

     List<User> findAll() throws SQLException;
     User getUserById(int id) throws SQLException;
     User getUserByName(String name) throws SQLException;
     User getUserByEmail(String name) throws SQLException;
     int insertUser(User user) throws SQLException;
     int deleteUser(int id) throws SQLException;
     int updateUser(User user) throws SQLException;

}
