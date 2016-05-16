package javase.t02.dao.datasource;

import javase.t02.dao.transfer.Metadata;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract interface for table METADATA
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface MetadataDAO {
    public int insert(Metadata metadata) throws SQLException;
    public int delete(int metadataId) throws SQLException;
    public int update(Metadata metadata) throws SQLException;
    public int getRecordCount() throws SQLException;
    public ResultSet selectAll() throws SQLException;
    public ResultSet selectByUserId(int userId) throws SQLException;
    public void closeResultSet() throws SQLException;
}