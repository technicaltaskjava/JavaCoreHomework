package singleton;

import org.junit.Test;
import singleton.DBInfo;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lammi on 23.04.2016.
 */
public class DBInfoTest {
    private DBInfo info1 = DBInfo.getInstance();
    private DBInfo info2 = DBInfo.getInstance();

    @Test
    public void testGetInstance(){
        assertEquals(true, info1 == info2);
    }
}
