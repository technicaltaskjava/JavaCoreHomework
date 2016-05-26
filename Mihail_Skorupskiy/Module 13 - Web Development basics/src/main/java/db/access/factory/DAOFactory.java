package db.access.factory;

import db.access.dao.DataAccessObject;
import db.exceptions.DAOCreationException;
import db.storage.DataTypes;

import java.sql.SQLException;

public interface DAOFactory {
     DataAccessObject getDAO(DataTypes type) throws DAOCreationException;
     void close(DataAccessObject dao) throws SQLException;
}
