package db.access.dao;

import db.exceptions.InsertionException;
import db.exceptions.SearchException;
import db.exceptions.UpdateException;
import db.storage.DataObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject {
    List<DataObject> getData() throws SQLException;
    void insertData(DataObject data) throws InsertionException;
    void updateData(int position, DataObject data) throws UpdateException;
    DataObject findData(String identifier) throws SearchException;
    Connection getConnection();
}
