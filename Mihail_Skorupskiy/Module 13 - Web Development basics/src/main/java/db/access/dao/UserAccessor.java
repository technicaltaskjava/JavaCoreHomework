package db.access.dao;

import db.exceptions.InsertionException;
import db.exceptions.InvalidDataTypeException;
import db.exceptions.SearchException;
import db.exceptions.UpdateException;
import db.storage.DataObject;
import db.storage.DataTypes;
import db.storage.data.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserAccessor extends AbstractAccessor implements DataAccessObject {

    public UserAccessor(Connection connection){
        super(connection);
    }

    @Override
    public List<DataObject> getData() throws SQLException {
        LinkedList<DataObject> list = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("select * from Users");
        results.beforeFirst();
        while (results.next()){
            list.add(new User(results.getString(1), results.getString(2), results.getString(3)));
        }
        results.close();
        statement.close();
        return list;
    }

    @Override
    public void updateData(int position, DataObject data) throws UpdateException {
        try (Statement update = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            if (data.getType().equals(DataTypes.METADATA)){
                User user = (User) data;
                ResultSet table = update.executeQuery("select * from Users");
                table.absolute(position);
                table.updateString(1, user.getUsername());
                table.updateString(2, user.getEmail());
                table.updateString(3, user.getPassword());
                table.updateRow();
                table.close();
            } else throw new InvalidDataTypeException();
        } catch (Exception e){
            throw new UpdateException(e);
        }
    }

    @Override
    public void insertData(DataObject data) throws InsertionException {
        try (PreparedStatement insert = connection.prepareStatement("insert into Users values (?, ?, ?)")) {
            if (data.getType().equals(DataTypes.USER)) {
                User user = (User) data;
                insert.setString(1, user.getUsername());
                insert.setString(2, user.getEmail());
                insert.setString(3, user.getPassword());
                insert.execute();
            } else throw new InvalidDataTypeException();
        } catch (Exception e){
            throw new InsertionException(e);
        }
    }

    @Override
    public DataObject findData(String identifier) throws SearchException {
        try (PreparedStatement find = connection.prepareStatement("select * from Users where username = ?")){
            find.setString(1, identifier);
            ResultSet results = find.executeQuery();
            if (results.first()) {
                return new User(results.getString(1), results.getString(2), results.getString(3));
            } else {
                return null;
            }
        } catch (Exception e){
            throw new SearchException(e);
        }
    }
}
