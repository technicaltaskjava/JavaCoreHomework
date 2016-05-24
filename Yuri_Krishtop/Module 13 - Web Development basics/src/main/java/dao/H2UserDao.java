package dao;

import entities.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public class H2UserDao extends AbstractJDBCDao<User> implements GenericDao<User> {

    private static final Logger log = Logger.getLogger(H2UserDao.class);
    public H2UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Users";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO Users (login, pass, email) VALUES (?, ?, ?);";
    }

    @Override
    public String getTableName() {
        return "Users";
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(3, object.getEmail());
        statement.setString(2, object.getPass());
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<User> result = new LinkedList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setPass(rs.getString("pass"));
            result.add(user);
        }
        return result;
    }

    public boolean isLoginUsed(String name) {
        String sql = "SELECT * FROM Users WHERE login = ?" ;
        List<User> listLog = null;
        int sizeLog = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            listLog =  parseResultSet(rs);
            sizeLog = listLog.size();
        } catch (SQLException e) {
            log.error(e);
        }
        return sizeLog != 0;
    }

    public boolean isEmailUsed(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?" ;
        List<User> listEmail = null;
        int sizeEmail = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            listEmail =  parseResultSet(rs);
            sizeEmail = listEmail.size();
        } catch (SQLException e) {
            log.error(e);
        }
        return sizeEmail != 0;
    }

    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM Users WHERE login = ?" ;
        List<User> list = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            list =  parseResultSet(rs);
            return list.get(0);
        } catch (SQLException e) {
            log.error(e);
            return new User();
        }
    }

}
