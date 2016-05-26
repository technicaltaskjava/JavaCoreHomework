package db.access.dao;

import db.exceptions.InsertionException;
import db.exceptions.InvalidDataTypeException;
import db.exceptions.SearchException;
import db.exceptions.UpdateException;
import db.storage.DataObject;
import db.storage.DataTypes;
import db.storage.data.Metadata;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MetadataAccessor extends AbstractAccessor implements DataAccessObject {

    public MetadataAccessor(Connection connection){
        super(connection);
    }

    @Override
    public List<DataObject> getData() throws SQLException {
        LinkedList<DataObject> list = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("select * from Metadata");
        results.beforeFirst();
        while (results.next()){
            list.add(new Metadata(results.getInt(1), results.getString(2), results.getTimestamp(3)));
        }
        results.close();
        statement.close();
        return list;
    }

    @Override
    public void updateData(int position, DataObject data) throws UpdateException {
        try (Statement update = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            if (data.getType().equals(DataTypes.METADATA)){
                Metadata metadata = (Metadata) data;
                ResultSet table = update.executeQuery("select * from Metadata");
                table.absolute(position);
                table.updateInt(1, metadata.getCookieId());
                table.updateString(2, metadata.getUserId());
                table.updateTimestamp(3, metadata.getTimeAdded());
                table.updateRow();
                table.close();
            } else throw new InvalidDataTypeException();
        } catch (Exception e){
            throw new UpdateException(e);
        }
    }

    @Override
    public void insertData(DataObject data) throws InsertionException {
        try (PreparedStatement insert = connection.prepareStatement("insert into Metadata values (?, ?, ?)")) {
            if (data.getType().equals(DataTypes.METADATA)) {
                Metadata metadata = (Metadata) data;
                insert.setInt(1, metadata.getCookieId());
                insert.setString(2, metadata.getUserId());
                insert.setTimestamp(3, metadata.getTimeAdded());
                insert.execute();
            } else throw new InvalidDataTypeException();
        } catch (Exception e){
            throw new InsertionException(e);
        }
    }

    //placeholder
    @Override
    public DataObject findData(String identifier) throws SearchException{
        return null;
    }
}
