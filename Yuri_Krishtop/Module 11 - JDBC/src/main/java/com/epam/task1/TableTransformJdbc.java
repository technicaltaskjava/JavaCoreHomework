package com.epam.task1;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Yuriy Krishtop on 05.05.2016.
 */
public class TableTransformJdbc {

    private static final String REG_DATE = "regDate";
    private static final String DOB = "DOB";
    private static final Logger log = Logger.getLogger(TableTransformJdbc.class);
    public static final String FORMAT_TABLE_ROW = "%-4s %-10s %-10s %-10s %-25s %-10s %-12s %-12s %-15s%n";
    public static final String LINE = "+---------------------------------------------------------------------------" +
            "---------------------------------------+";

    private TableTransformJdbc() {
    }

    public static void main() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            String command = "SELECT * FROM Users";
            ps = con.prepareStatement(command, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            showTable(rs);
            System.out.println("Updating \"name\" for id = 1:");
            updateTableRow(rs, 1, "name", "Olga");
            showTable(rs);
            System.out.println("Updating \"DOB\" for id = 2:");
            updateTableRow(rs, 2, "DOB", "1970-01-01");
            showTable(rs);
            System.out.println("Selecting row for id = 3:");
            selectRow(rs, 3);
            System.out.println("Inserting new row:");
            String[] values = {"newlogin", "newname", "newsurname", "new@email.com", "dfho83", "1987-04-17",
                    "1015-02-22", "+380663940244"};
            insertNewRow(rs, values);
            rs = ps.executeQuery();
            showTable(rs);
            System.out.println("Deleting row for id = 1:");
            deleteRowFromTable(rs, 1);
            rs = ps.executeQuery();
            showTable(rs);
            System.out.println("Deleting table:");
            st = con.createStatement();
            String delCommand = "DROP TABLE Users";
            st.executeUpdate(delCommand);
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
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }

    public static void showTable(ResultSet rs) throws SQLException {
        printCapTable();
        rs.beforeFirst();
        while (rs.next()) {
            System.out.printf(FORMAT_TABLE_ROW, "| " + rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getDate(8),
                    rs.getString(9) + " |");
        }
        System.out.println(LINE);
    }

    public static void updateTableRow(ResultSet rs, int id, String colName, String value) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            if (rs.getInt(1) == id) {
                if (DOB.equals(colName) || REG_DATE.equals(colName)) {
                    rs.updateDate(colName, Date.valueOf(value));
                } else {
                    rs.updateString(colName, value);
                }
                rs.updateRow();
                break;
            }
        }
    }

    public static void selectRow(ResultSet rs, int id) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            if (rs.getInt(1) == id) {
                printCapTable();
                System.out.printf(FORMAT_TABLE_ROW, "| " + rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getDate(8),
                        rs.getString(9) + " |");
                System.out.println(LINE);
                break;
            }
        }
    }

    public static void insertNewRow(ResultSet rs, String[] values) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("login", values[0]);
        rs.updateString("name", values[1]);
        rs.updateString("surname", values[2]);
        rs.updateString("email", values[3]);
        rs.updateString("pass", values[4]);
        rs.updateDate(DOB, Date.valueOf(values[5]));
        rs.updateDate(REG_DATE, Date.valueOf(values[6]));
        rs.updateString("phone", values[7]);
        rs.insertRow();
        rs.moveToCurrentRow();
    }

    public static void deleteRowFromTable(ResultSet rs, int id) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            if (rs.getInt(1) == id) {
                rs.deleteRow();
                break;
            }
        }
    }

    public static void printCapTable() {
        System.out.println(LINE);
        System.out.printf(FORMAT_TABLE_ROW, "| id", "login", "name", "surname", "email", "pass",
                "DOB", "regDate", "phone" + "         |");
        System.out.println(LINE);
    }

}