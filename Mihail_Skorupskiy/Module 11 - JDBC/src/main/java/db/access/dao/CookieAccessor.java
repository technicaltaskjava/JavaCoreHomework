package db.access.dao;

import db.exceptions.InsertionException;
import db.exceptions.InvalidDataTypeException;
import db.exceptions.UpdateException;
import db.storage.DataObject;
import db.storage.DataTypes;
import db.storage.data.Cookie;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CookieAccessor extends AbstractAccessor implements DataAccessObject {

    public CookieAccessor(Connection connection){
        super(connection);
    }

    @Override
    public List<DataObject> getData() throws SQLException {
        LinkedList<DataObject> list = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("select * from Cookies");
        results.beforeFirst();
        while (results.next()){
            list.add(new Cookie(results.getInt(1), results.getString(2), results.getInt(3), results.getString(4)));
        }
        results.close();
        statement.close();
        return list;
    }

    @Override
    public void updateData(int position, DataObject data) throws UpdateException {
        try (Statement update = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            if (data.getType().equals(DataTypes.COOKIE)){
                Cookie cookie = (Cookie) data;
                ResultSet table = update.executeQuery("select * from Cookies");
                table.absolute(position);
                table.updateInt(1, cookie.getId());
                table.updateString(2, cookie.getName());
                table.updateInt(3, cookie.getPrice());
                table.updateString(4, cookie.getMessage());
                table.updateRow();
                table.close();
            } else throw new InvalidDataTypeException();
        } catch (Exception e){
            throw new UpdateException(e);
        }
    }

    @Override
    public void insertData(DataObject data) throws InsertionException {
        try (PreparedStatement insert = connection.prepareStatement("insert into Cookies values (?, ?, ?, ?)")) {
            if (data.getType().equals(DataTypes.COOKIE)) {
                Cookie cookie = (Cookie) data;
                insert.setInt(1, cookie.getId());
                insert.setString(2, cookie.getName());
                insert.setInt(3, cookie.getPrice());
                insert.setString(4, cookie.getMessage());
                insert.execute();
            } else throw new InvalidDataTypeException();
        } catch (Exception e){
            throw new InsertionException(e);
        }
    }
}
