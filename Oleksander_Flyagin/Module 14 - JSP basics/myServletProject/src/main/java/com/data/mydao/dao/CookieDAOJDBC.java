package com.data.mydao.dao;
import com.data.cookie.Cookie;
import com.data.mydao.exeptions.MyJDBCException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CookieDAOJDBC implements CookieDAO {


    private Statement statement;
    private ResultSet resultSet;


    public CookieDAOJDBC (Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    private static String sqlFindForID(int id) {
        return "SELECT id, PREDICTIONS FROM Cookie WHERE id = " + id +";";
    }
    private static String sqlfindForIdlPrediction(int id, String prediction) {
        return "SELECT id, PREDICTIONS  FROM Cookie  WHERE id = " + "'" + id + "'"
                + " AND prediction =" + "'" + prediction + "'" + ";";
    }

    private static String sqlCreate(Cookie cookie) {
        return "INSERT INTO Cookie (PREDICTIONS) values (" + "'" + cookie.getPrediction() + "'"
                +");";
    }
    private static String sqlNewPrediction(String cookie) {
        return "INSERT INTO Cookie (PREDICTIONS) values (" + "'" + cookie + "'"+");";
    }

    private static String sqlUpdate(Cookie cookie) {
        return "UPDATE COOKIE SET PREDICTIONS ='" + cookie.getPrediction()+"' WHERE ID="+ cookie.getId()+";";
    }

    private static String sqlDellet(int id) {
        return "DELETE FROM Cookie WHERE ID = '" + id + "';";
    }

    private static String sqlExistID(int id) {
        return "SELECT id FROM Cookie WHERE  id =" + id + ";";
    }

    private String sqlChangePrediction(Cookie cookie) {
        return "update Cookie set PREDICTIONS =" + "'" + cookie.getPrediction() + "'"
                + "where id = " + cookie.getId() + ";";
    }


    private String sqlCountPrediction() {
        return  " SELECT count(id)from COOKIE;";
    }

    private String sqlListPrediction() {
        return  " SELECT * from COOKIE;";
    }


    private Cookie selectMath(String select) {
        Cookie cookie = null;
        try {
            resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                cookie = map(resultSet);
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return cookie;
    }

    @Override
    public List<Cookie> cookieList() {
        List<Cookie>cookies = new ArrayList();
        try {
            resultSet = statement.executeQuery(sqlListPrediction());
            while (resultSet.next()) {
                cookies.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return cookies;
    }


    private static Cookie map(ResultSet resultSet) {
        Cookie cookie;
        try {
            cookie = new Cookie(resultSet.getInt("ID"),
                    resultSet.getString("predictions"));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return cookie;
    }

    @Override
    public int countPrediction() {
        int resoult= 0;
        try {
            resultSet = statement.executeQuery(sqlCountPrediction());
            while (resultSet.next()) {
                resoult = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return resoult;

    }



    @Override
    public Cookie findForID(int id) {
        return selectMath(sqlFindForID(id));

    }
    @Override
    public Cookie findForIdlPrediction(int id, String prediction) {
        return selectMath(sqlfindForIdlPrediction( id,  prediction));
    }


    @Override
    public void create(Cookie cookie) {
        try {
            statement.execute(sqlCreate(cookie));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }


    }

    @Override
    public void addPrediction(String cookie) {
        try {
            statement.execute(sqlNewPrediction(cookie));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }

    }

    @Override
    public void update(Cookie cookie) {
        try {
            int affectedRows = statement.executeUpdate(sqlUpdate(cookie));
            if (affectedRows == 0) {
                throw new MyJDBCException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {

            int affectedRows = statement.executeUpdate(sqlDellet(id));
            if (affectedRows == 0)
                throw new MyJDBCException("Deleting user failed, no rows affected.");
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }

    }

    @Override
    public boolean existID(int id) {
        boolean exist;
        try {
            resultSet = statement.executeQuery(sqlExistID(id));
            exist = resultSet.next();
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return exist;
    }

    @Override
    public void changePrediction(Cookie cookie) {
        try {
            int affectedRows = statement.executeUpdate(sqlChangePrediction(cookie));
            if (affectedRows == 0)
                throw new MyJDBCException("Changing password failed, no rows affected.");
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }


    }

    @Override
    public int getID(Cookie cookie) {
        return findForIdlPrediction(cookie.getId(),cookie.getPrediction()).getId();
    }
}




