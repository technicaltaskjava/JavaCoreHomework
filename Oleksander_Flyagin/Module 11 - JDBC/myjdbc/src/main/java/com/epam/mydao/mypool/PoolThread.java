package com.epam.mydao.mypool;


import com.epam.mydao.dao.UserDAO;
import com.epam.mydao.dao.UserDAOJDBC;
import com.epam.mydao.exeptions.MyJDBCException;
import com.epam.mydao.user.Users;
import java.sql.Connection;
import java.sql.SQLException;



public class  PoolThread extends Thread {
    private Connection setConnection;
    private Users user;
    private String nameThread;


    public PoolThread(Connection setConnection, Users user) {
        this.user = user;
        this.setConnection = setConnection;
        this.nameThread = this.getName();
        this.start();

    }

    private void connectTo() {
        try {
            System.out.println("Start " + nameThread);
            UserDAO userDAO = new UserDAOJDBC(setConnection);
            System.out.println("UserDAO successfully obtained: " + userDAO);
            userDAO.create(user);
            System.out.println("User successfully created: " + user);
            boolean exist = userDAO.existEmail(user.getEmail());
            System.out.println("This email should not exist anymore, so this should print false: " + exist);
            user.setPassword("*************");
            userDAO.changePassword(user);
            System.out.println("Another user's password successfully changed: " + userDAO.findForID(user.getId()));
            user.setEmail("aaa@o.omg");
            userDAO.update(user);
            System.out.println("User successfully updated: " + user);
            System.out.println("Find User for ID: " + user.getId() + "\r\n" + userDAO.findForID(user.getId()));
            System.out.println("User successfully deleted: " + user);
            userDAO.delete(user);
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }



    @Override
    public void run() {
        connectTo();


    }
}