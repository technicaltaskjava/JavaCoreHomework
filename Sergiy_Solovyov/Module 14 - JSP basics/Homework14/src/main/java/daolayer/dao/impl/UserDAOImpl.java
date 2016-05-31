package daolayer.dao.impl;

import daolayer.dao.UserDAO;
import daolayer.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static daolayer.dao.Constant.*;

/**
 * @author Sergey Solovyov
 */
public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM USERS;");

        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt(ID));
            user.setUserName(rs.getString(USERNAME));
            user.setEmail(rs.getString(EMAIL));
            user.setPassword(rs.getString(PASSW));
            user.setAge(rs.getInt(AGE));
            users.add(user);
        }
        rs.close();
        st.close();
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM USERS WHERE ID =?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setUserName(rs.getString(USERNAME));
        user.setPassword(rs.getString(PASSW));
        user.setEmail(rs.getString(EMAIL));
        user.setAge(rs.getInt(AGE));
        rs.close();
        ps.close();
        return user;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM USERS WHERE username =?");
        ps.setString(1 , name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setUserName(rs.getString(USERNAME));
        user.setPassword(rs.getString(PASSW));
        user.setEmail(rs.getString(EMAIL));
        user.setAge(rs.getInt(AGE));
        rs.close();
        ps.close();
        return user;
    }

    @Override
    public User getUserByEmail(String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM USERS WHERE email =?");
        ps.setString(1 , name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setUserName(rs.getString(USERNAME));
        user.setPassword(rs.getString(PASSW));
        user.setEmail(rs.getString(EMAIL));
        user.setAge(rs.getInt(AGE));
        rs.close();
        ps.close();
        return user;
    }

    @Override
    public int insertUser(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO `users` (`username`, `email`, `password`, `age`) VALUES (?, ?, ?, ?)");
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setInt(4, user.getAge());
        int count =  ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int deleteUser(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("DELETE FROM USERS WHERE id=?");
        ps.setInt(1, id);
        int count = ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int updateUser(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("UPDATE USERS SET `username`=?, `email`=?, `password`=?, `age`=? WHERE `id`=?");

        ps.setString(1, user.getUserName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setInt(4, user.getAge());
        ps.setInt(5, user.getId());
        int count =  ps.executeUpdate();
        ps.close();
        return count;
    }
}
