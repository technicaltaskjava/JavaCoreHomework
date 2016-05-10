package dao.accessor;

import dao.DAO;
import dao.entity.Metadata;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Ushakov
 */
public class MetadataAccessorTest {
    private static MetadataAccessor accessor = DAO.getInstance().getMetadataAccessor();
    private static Metadata testMetadata;

    @BeforeClass
    public static void start() {
        testMetadata = new Metadata(11, 11);
        testMetadata.setTimeAdded(new Date(1462654800000L));
    }

    @Test
    public void testGetMetadataList() {
        List<Metadata> metadataList = accessor.getMetadataList();

        assertEquals(metadataList.size(), accessor.size());
    }

    @Test
    public void testGetMetadataByCookieID() {
        List<Metadata> metadataList = accessor.getMetadataByCookieID(1);

        for (Metadata metadata : metadataList) {
            assertEquals(metadata.getCookieID(), 1);
        }
    }

    @Test
    public void testGetMetadataByUserID() {
        List<Metadata> metadataList = accessor.getMetadataByUserID(1);

        for (Metadata metadata : metadataList) {
            assertEquals(metadata.getUserID(), 1);
        }
    }

    @Test
    public void testGetMetadataByTimeAdded() {
        List<Metadata> metadataList = accessor.getMetadataByTimeAdded("2016-05-11");

        for (Metadata metadata : metadataList) {
            assertEquals(metadata.getTimeAdded().getTime(), 1462914000000L);
        }
    }

    @Test
    public void testAddMetadata() {
        int beforeAdd = accessor.size();

        accessor.addMetadata(testMetadata);

        int afterAdd = accessor.size();

        assertTrue(beforeAdd < afterAdd);

    }

    @Test
    public void testDeleteMetadata() {
        accessor.addMetadata(testMetadata);


        int beforeDelete = accessor.size();

        accessor.deleteMetadata(testMetadata);

        int afterDelete = accessor.size();

        assertTrue(beforeDelete > afterDelete);

    }
}