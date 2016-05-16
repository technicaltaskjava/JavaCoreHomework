package db.connection;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyConnectionPool {

    private BlockingQueue<Connection> pool;

    public MyConnectionPool(int size, String url, String username, String password) throws SQLException{
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++){
            Connection temp = DriverManager.getConnection(url, username, password);
            pool.add(temp);
        }
    }

    public Connection getConnection() throws InterruptedException{
        return pool.take();
    }

    public int getSize(){
        return pool.size();
    }

    public void put(Connection connection) throws SQLException{
        if (connection != null){
            pool.offer(connection);
        }
    }

    public void closeAll() throws SQLException{
        for (Connection connection : pool) {
            connection.close();
        }
    }
}

