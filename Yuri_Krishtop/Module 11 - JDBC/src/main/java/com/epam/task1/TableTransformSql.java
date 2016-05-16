package com.epam.task1;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yuriy Krishtop on 07.05.2016.
 */
public class TableTransformSql {

    private static final Logger log = Logger.getLogger(TableTransformJdbc.class);
    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    private TableTransformSql() {
    }

    public static void main() {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            showTable();
            System.out.println("Updating \"name\" for id = 1:");
            runSqlCommand("UPDATE Users SET name = 'Olga' WHERE id = 1;");
            showTable();
            System.out.println("Updating \"DOB\" for id = 2:");
            runSqlCommand("UPDATE Users SET DOB = '1970-01-01' WHERE id = 2;");
            showTable();
            System.out.println("Selecting row for id = 3:");
            String command = "SELECT * FROM Users WHERE id = 3;";
            ps = con.prepareStatement(command);
            rs = ps.executeQuery();
            TableTransformJdbc.printCapTable();
            rs.next();
            System.out.printf(TableTransformJdbc.FORMAT_TABLE_ROW, "| " + rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getDate(8), rs.getString(9) + " |");
            System.out.println(TableTransformJdbc.LINE);
            System.out.println("Inserting new row:");
            runSqlCommand("INSERT INTO Users (login, name, surname, email, pass, DOB, regDate, phone) VALUES" +
                    "('newlog', 'newname', 'newsurname', 'new@email.com', 'newpass', '2000-01-01', '2000-01-01', '+380000000000');");
            showTable();
            System.out.println("Deleting row for id = 1:");
            runSqlCommand("DELETE FROM Users WHERE id = 1;");
            showTable();
            System.out.println("Deleting table:");
            runSqlCommand("DROP TABLE Users;");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }

    public static void showTable() throws SQLException {
        String command = "SELECT * FROM Users";
        ps = con.prepareStatement(command);
        rs = ps.executeQuery();
        TableTransformJdbc.printCapTable();
        rs.beforeFirst();
        while (rs.next()) {
            System.out.printf(TableTransformJdbc.FORMAT_TABLE_ROW, "| " + rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),
                    rs.getDate(8), rs.getString(9) + " |");
        }
        System.out.println(TableTransformJdbc.LINE);
    }

    public static void runSqlCommand(String command) throws SQLException {
        ps = con.prepareStatement(command);
        ps.executeUpdate();
    }

}
