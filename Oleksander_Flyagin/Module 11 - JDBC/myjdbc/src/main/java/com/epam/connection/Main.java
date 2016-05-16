package com.epam.connection;



import java.sql.SQLException;


public class Main {
    private Main() {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MyConnection myConnection = new MyConnection();

        System.out.println("Show full info");
        myConnection.select();
        System.out.println("add user");
        myConnection.insert("00718", "dod@.cu.com");
        System.out.println("Show new  info");
        myConnection.select();
        System.out.println("Update user");
        myConnection.update("11000", 5);
        System.out.println("Show user with id 5");
        myConnection.selectID(5);
        System.out.println("delet user  with email dod....");
        myConnection.dell("dod");
        System.out.println("Show new  info");
        myConnection.select();
        System.out.println("DROP TABLE");
        myConnection.dropTable();

    }
}
