package daotask.dao.impl;

import daotask.dao.MetadataDAO;
import daotask.entity.Metadata;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jdbctask.Constant.*;


/**
 * @author Sergey Solovyov
 */
public class MetadataDAOImpl implements MetadataDAO {

    private Connection connection;

    public MetadataDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Metadata> findAll() throws SQLException {
        List<Metadata> metadatas = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM METADATA;");

        while (rs.next()){
            Metadata metadata = new Metadata();
            metadata.setId(rs.getInt(ID));
            metadata.setCookieId(rs.getInt(COOKIE_ID));
            metadata.setUserId(rs.getInt(USER_ID));
            metadata.setTimeAdded(rs.getTimestamp(TIME_ADDED));
            metadatas.add(metadata);
        }
        rs.close();
        st.close();
        return metadatas;
    }

    @Override
    public Metadata getMetadataById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM METADATA WHERE ID =?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Metadata metadata = new Metadata();
        metadata.setId(rs.getInt(ID));
        metadata.setCookieId(rs.getInt(COOKIE_ID));
        metadata.setUserId(rs.getInt(USER_ID));
        metadata.setTimeAdded(rs.getTimestamp(TIME_ADDED));
        rs.close();
        ps.close();
        return metadata;
    }

    @Override
    public int insertMetadata(Metadata metadata) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO `metadata` (`cookie_id`, `user_id`, `time_added`) VALUES (?, ?, ?)");
        ps.setInt(1, metadata.getCookieId());
        ps.setInt(2, metadata.getUserId());
        ps.setTimestamp(3, metadata.getTimeAdded());
        int count =  ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int deleteMetadata(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("DELETE FROM METADATA WHERE id=?");
        ps.setInt(1, id);
        int count = ps.executeUpdate();
        ps.close();
        return count;
    }

    @Override
    public int updateMetadata(Metadata metadata) throws SQLException {
        PreparedStatement ps = connection.prepareStatement
                ("UPDATE USERS SET `cookie_id`=?, `user_id`=?, `time_added`=? WHERE `id`=?");

        ps.setInt(1, metadata.getCookieId());
        ps.setInt(2, metadata.getUserId());
        ps.setTimestamp(3, metadata.getTimeAdded());
        ps.setInt(4, metadata.getId());
        int count =  ps.executeUpdate();
        ps.close();
        return count;
    }
}
