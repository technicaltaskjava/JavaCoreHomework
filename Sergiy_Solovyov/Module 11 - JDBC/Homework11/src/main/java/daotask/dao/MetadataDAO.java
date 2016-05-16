package daotask.dao;

import daotask.entity.Metadata;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lammi on 10.05.2016.
 */
public interface MetadataDAO {

    List<Metadata> findAll() throws SQLException;
    Metadata getMetadataById(int id) throws SQLException;
    int insertMetadata(Metadata metadata) throws SQLException;
    int deleteMetadata(int id) throws SQLException;
    int updateMetadata(Metadata metadata) throws SQLException;
}
