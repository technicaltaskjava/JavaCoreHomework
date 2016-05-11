package task1;

import java.sql.*;

public class WorkWithDB {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        updateDataInDB("UpdatedLastName2", "UpdatedFrstName2", "UpdatedAddress2", "user2");
        updateDataInDB("UpdatedLastName3", "UpdatedFrstName3", "UpdatedAddress3", "user3");
        insertDataInDB();
        getAllDataFromDB();
        getSomeData("user2");
        deleteTable();
    }

    public static void deleteTable() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement statement = conn.createStatement();
       int r = statement.executeUpdate("CREATE TABLE TestForDel;");

       ResultSet rs = statement.executeQuery("SHOW TABLES;");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        r = statement.executeUpdate("DROP TABLE IF EXISTS TestForDel;");
        rs = statement.executeQuery("SHOW TABLES;");
        if(!rs.next()){
            System.out.println("Table dropped!");
        }
        statement.close();
        rs.close();
        conn.close();
    }

    public static void getSomeData(String userName) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stat = conn.createStatement();
        String sql = "select FIRSTNAME, LASTNAME, COOOKIE from \"Fortune cookies\".METADATA \n" +
                "INNER\n" +
                "join \"Fortune cookies\".USERS as us  ON METADATA.USER_ID  = us.USER_ID \n" +
                "INNER \n" +
                "join \"Fortune cookies\".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID \n" +
                "where us.USERNAME = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +  rs.getString(3));
        }
        ps.close();
        stat.close();
        conn.close();
    }

    public static void insertDataInDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stat = conn.createStatement();
        String sql1 = "INSERT INTO \"Fortune cookies\".COOKIES (COOKIE_ID, COOOKIE)\n" +
                "VALUES(?, ?)";
        String sql2 = "insert into \"Fortune cookies\".USERS (USER_ID, USEREMAIL, USERNAME, USERPASSWORD  )\n" +
                "values(?, ?, ?, ?)";
        String sql3 = "insert into \"Fortune cookies\".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)\n" +
                "values(?, ?, ?)";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ps1.setInt(1, 112);
        ps1.setString(2, "CookieInsertedText111");
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setInt(1, 11);
        ps2.setString(2, "InsEmail@email.com");
        ps2.setString(3, "InsertedUserName");
        ps2.setString(4, "InsertedPassword");
        PreparedStatement ps3 = conn.prepareStatement(sql3);
        ps3.setInt(1, 11);
        ps3.setInt(2, 112);
        java.util.Date date= new java.util.Date();
        ps3.setTimestamp(3, new Timestamp(date.getTime()));
        ps1.execute();
        ps1.close();
        ps2.execute();
        ps2.close();
        ps3.execute();
        ps3.close();
        stat.close();
        conn.close();
    }

    public static void updateDataInDB(String lastName, String firstName, String address, String username) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stat = conn.createStatement();
        String sql = "UPDATE \"Fortune cookies\".USERS \n" +
                "SET LASTNAME = ?, FIRSTNAME  = ?, ADDRESS = ?\n" +
                "WHERE USERNAME = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, lastName);
        ps.setString(2, firstName);
        ps.setString(3, address);
        ps.setString(4, username);
        ps.executeUpdate();
        ps.close();
        stat.close();
        conn.close();
    }

    public static void getAllDataFromDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stat = conn.createStatement();
        ResultSet rs;
        rs = stat.executeQuery("select USERNAME, FIRSTNAME, LASTNAME, COOOKIE, TIME_ADDED  from \"Fortune cookies\".METADATA \n" +
                "join \"Fortune cookies\".USERS as u ON METADATA.USER_ID  = u.USER_ID \n" +
                "join \"Fortune cookies\".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID ");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +  rs.getString(3)+ " " +  rs.getString(4));
        }
        rs.close();
        stat.close();
        conn.close();
    }



}
