package Task1;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleg on 15.03.2016.
 */
public class DirectoryViewerTest {

    @Test

    public void test() throws Exception{

        File[] files = DirectoryViewer.getDirectoryElements("src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "testDir" + File.separator);

        assertEquals(2, files.length);



    }

}
