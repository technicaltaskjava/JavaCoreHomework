package logic;

import db.access.dao.DataAccessObject;
import db.access.factory.DAOFactory;
import db.access.factory.sql.DatabaseAccessorFactory;
import db.connection.MyConnectionPool;
import db.exceptions.DAOCreationException;
import db.exceptions.InsertionException;
import db.exceptions.UpdateException;
import db.storage.DataObject;
import db.storage.DataTypes;
import db.storage.data.Cookie;

import java.sql.*;
import java.util.List;

public class Main {

    private static final String GET_USERS = "select * from Users";

    private Main(){}

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Driver not found. " + e);
            System.exit(1);
        }

        //Task 1.
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")){

            Statement statement = connection.createStatement();
            showResults(statement.executeQuery(GET_USERS));
            statement.close();

            //извлеките информацию из таблицы с помощью подготовленного запроса
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Cookies where id = ?");
            preparedStatement.setInt(1, (int)(Math.random()*10));
            showResults(preparedStatement.executeQuery());
            preparedStatement.close();

            //обновите несколько записей в таблице
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet updatedTable = statement.executeQuery("select * from Users where username like 'User%'");
            updatedTable.absolute(5);
            updatedTable.updateString(3, "asd@zxc.f");
            updatedTable.updateRow();
            updatedTable.close();
            statement.close();

            //выберите конкретную запись в таблице
            statement = connection.createStatement();
            showResults(statement.executeQuery("select * from Users where username = 'Vasya'"));
            statement.close();

            //вставьте новую запись в таблицу
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            updatedTable = statement.executeQuery(GET_USERS);
            updatedTable.moveToInsertRow();
            updatedTable.updateString(1, "Innokentiy");
            updatedTable.updateString(2, "bbq@wazup.eh");
            updatedTable.updateString(3, "__Sup3rPass-w0rd.69!");
            updatedTable.insertRow();
            updatedTable.close();
            statement.close();

            statement = connection.createStatement();
            showResults(statement.executeQuery(GET_USERS));
            statement.close();

            //удалите таблицу.
            statement = connection.createStatement();
            statement.execute("create table Test (columnOne int, columnTwo varchar)");
            showResults(statement.executeQuery("select * from Test"));
            statement.execute("drop table Test");
            showResults(statement.executeQuery("show tables"));
            statement.close();


        } catch (SQLException e) {
            System.out.println("Error. " + e);
        }

        //Task 2.
        try {
            MyConnectionPool pool = new MyConnectionPool(5, "jdbc:h2:~/test", "sa", "");
            DAOFactory factory = new DatabaseAccessorFactory(pool);
            DataAccessObject cookieWriter = factory.getDAO(DataTypes.COOKIE);
            Cookie cookie = new Cookie(150, "Coffee-flavored cookie", 11, "Java is fun. Occasionally :)");
            cookieWriter.insertData(cookie);
            List<DataObject> cookies = cookieWriter.getData();
            for (DataObject iCookie : cookies) {
                System.out.print(iCookie.get());
            }
            factory.close(cookieWriter);
            DataAccessObject userReader = factory.getDAO(DataTypes.USER);
            List<DataObject> users = userReader.getData();
            for (DataObject user : users) {
                System.out.print(user.get());
            }
            factory.close(userReader);
            DataAccessObject cookieUpdater = factory.getDAO(DataTypes.COOKIE);
            cookie = new Cookie(5, "Most up-to-date cookie", 10, "Pray daily for backup success if you like updates.");
            cookieUpdater.updateData(5, cookie);
            factory.close(cookieUpdater);
            pool.closeAll();
        } catch (DAOCreationException daoe){
            System.out.println("Data access object was not created. " + daoe + "\n" + daoe.getCause());
        } catch (InsertionException ie){
            System.out.println("Data was not added to the table. " + ie + "\n" + ie.getCause());
        } catch (UpdateException ue){
            System.out.println("Data was not updated. " + ue + "\n" + ue.getCause());
        } catch (SQLException sqle){
            System.out.println("Database error. " + sqle);
        }


    }

    private static void showResults(ResultSet results) throws SQLException{
        ResultSetMetaData metaData = results.getMetaData();
        int columnNumber = metaData.getColumnCount();
        System.out.println();
        for (int i = 0; i < columnNumber; i++){
            System.out.print(metaData.getColumnLabel(i+1) + "\t");
        }
        System.out.println();
        results.beforeFirst();
        while (results.next()){
            for (int i = 0; i < columnNumber; i++){
                System.out.print(results.getString(i+1) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
