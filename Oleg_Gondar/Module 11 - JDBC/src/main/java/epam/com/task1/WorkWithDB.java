package epam.com.task1;

import org.apache.log4j.Logger;

import java.sql.*;

public class WorkWithDB {

    private static final Logger logger = Logger.getLogger(String.valueOf(WorkWithDB.class));

    private WorkWithDB() {
    }

    public static void deleteTable() {
        Connection conn = createConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE TestForDel;");
            ResultSet rs = statement.executeQuery("SHOW TABLES;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            statement.executeUpdate("DROP TABLE IF EXISTS TestForDel;");
            rs = statement.executeQuery("SHOW TABLES;");
            if (!rs.next()) {
                System.out.println("Table dropped!");
            }
            statement.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            log(e);
        }
    }

    public static void getSomeData(String userName) {

        Connection conn = createConnection();
        String sql = "select FIRSTNAME, LASTNAME, COOOKIE from \"Fortune cookies\".METADATA \n" +
                "INNER\n" +
                "join \"Fortune cookies\".USERS as us  ON METADATA.USER_ID  = us.USER_ID \n" +
                "INNER \n" +
                "join \"Fortune cookies\".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID \n" +
                "where us.USERNAME = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            ps.close();
        } catch (SQLException e) {
            log(e);
        }
    }

    public static void insertDataInDB() {

        Connection conn = createConnection();

        String sql1 = "INSERT INTO \"Fortune cookies\".COOKIES (COOKIE_ID, COOOKIE)\n" +
                "VALUES(?, ?)";
        String sql2 = "insert into \"Fortune cookies\".USERS (USER_ID, USEREMAIL, USERNAME, USERPASSWORD, FIRSTNAME, LASTNAME  )\n" +
                "values(?, ?, ?, ?, ?, ?)";
        String sql3 = "insert into \"Fortune cookies\".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)\n" +
                "values(?, ?, ?)";
        PreparedStatement ps1 = null;
        try {
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, 11);
            ps1.setString(2, "CookieInsertedText");
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 11);
            ps2.setString(2, "InsertedEmail@email.com");
            ps2.setString(3, "InsertedUserName");
            ps2.setString(4, "InsertedPassword");
            ps2.setString(5, "InsertedFirstName");
            ps2.setString(6, "InsertedLastName");
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setInt(1, 11);
            ps3.setInt(2, 11);
            java.util.Date date = new java.util.Date();
            ps3.setTimestamp(3, new Timestamp(date.getTime()));
            ps1.execute();
            ps1.close();
            ps2.execute();
            ps2.close();
            ps3.execute();
            ps3.close();
            conn.close();
        } catch (SQLException e) {
            log(e);
        }
    }

    public static void updateDataInDB(String lastName, String firstName, String address, String username) {

        Connection conn = createConnection();
        String sql = "UPDATE \"Fortune cookies\".USERS \n" +
                "SET LASTNAME = ?, FIRSTNAME  = ?, ADDRESS = ?\n" +
                "WHERE USERNAME = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, address);
            ps.setString(4, username);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            log(e);
        }
    }

    public static void getAllDataFromDB() {

        Connection conn = createConnection();
        Statement stat = null;
        try {
            stat = conn.createStatement();
            ResultSet rs;
            rs = stat.executeQuery("select USERNAME, FIRSTNAME, LASTNAME, COOOKIE, TIME_ADDED  from \"Fortune cookies\".METADATA \n" +
                    "join \"Fortune cookies\".USERS as u ON METADATA.USER_ID  = u.USER_ID \n" +
                    "join \"Fortune cookies\".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID ");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            log(e);
        }
    }

    private static Connection createConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        } catch (SQLException | ClassNotFoundException e) {
            log(e);
        }
        return null;
    }

    private static void log(Exception e) {
        org.apache.log4j.BasicConfigurator.configure();
        logger.error(e);
    }
}
