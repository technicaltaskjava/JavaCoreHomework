package com.epam.connection;

import com.epam.mydao.exeptions.MyJDBCException;

import java.sql.*;


public class MyConnection {


    private static final String URI = "jdbc:h2:~/test";
    private static final String UNAME = "sa";
    private static final String PASSW = "";
    private Statement st = null;


    public MyConnection() {
        try {
            Connection connect = DriverManager.getConnection(URI, UNAME, PASSW);
            st = connect.createStatement();

        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    private  String sqlSelect() {
        return "SELECT * FROM USERS;";
    }

    private  String sqlInsert(String password, String email) {
        return "INSERT INTO users(PASSWORD, EMAIL) values (" + "'" + password + "'"
                + ", " + "'" + email + "'" + ", " + ");";
    }

    private  String sqlUpdate(String password, int userID) {
        return "update USERS set PASSWORD =" + "'" + password + "'" + "where id = " + userID + ";";
    }

    private  String sqlDell(String userEm) {
        return "DELETE FROM USERS WHERE EMAIL LIKE '" + userEm + "%';";
    }

    private  String sqlSelectID(int id) {
        return "SELECT * FROM USERS WHERE ID = " + id + ";";
    }

    private  String sqlDrop() {
        return " DROP TABLE USERS;";
    }


    private void showUsers(int id, String password, String email) {
        System.out.println(id + "   " + password + "   " + email);
    }

    public void select() {
        try {
            selectMath(sqlSelect());
        } catch (SQLException e) {
            throw  new MyJDBCException(e);
        }
    }

    public void insert(String password, String email) {

        try {
            st.execute(sqlInsert(password, email));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    public void update(String password, int userID) {
        try {
            st.executeUpdate(sqlUpdate(password, userID));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }


    public void dell(String userEm) {
        try {
            st.execute(sqlDell(userEm));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    public void selectID(int id) {
        try {
            selectMath(sqlSelectID(id));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    public void dropTable() {
        try {
            st.executeUpdate(sqlDrop());
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }


    private void selectMath(String select) throws SQLException {
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            showUsers(rs.getInt("ID"), rs.getString(2), rs.getString(3));
        }
        rs.close();
    }


}
