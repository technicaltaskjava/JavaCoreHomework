package htmlimagesearch;

import htmlimagesearch.exceptions.NotFileException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static junit.framework.Assert.assertEquals;

/**
 * @author Alexey Ushakov
 */
public class HTMLImageSearchTest {
    private HTMLImageSearch imageSearch;

    @Before
    public void onStartTest() throws Exception {
        imageSearch = new HTMLImageSearch();
    }

    @Test
    public void testLoad() throws Exception {
        File file = new File("./src/test/java/htmlimagesearch/inputhtml");
        imageSearch.load(file.getCanonicalPath());
    }

    @Test(expected = FileNotFoundException.class)
    public void testLoadNotExistingFile() throws Exception {
        File file = new File("./src/test/java/htmlimagesearch/inputhtml2");
        imageSearch.load(file.getCanonicalPath());
    }

    @Test(expected = NotFileException.class)
    public void testLoadFolder() throws Exception {
        File file = new File("./src/test/java/htmlimagesearch/");
        imageSearch.load(file.getCanonicalPath());
    }

    @Test
    public void testGetLinksOnImage() throws Exception {
        File file = new File("./src/test/java/htmlimagesearch/inputhtml");
        imageSearch.load(file.getCanonicalPath());
        assertEquals(imageSearch.getLinksOnImage().length, 137);
    }
}