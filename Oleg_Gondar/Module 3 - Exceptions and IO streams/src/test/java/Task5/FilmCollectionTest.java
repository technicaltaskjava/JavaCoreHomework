package Task5;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Oleg on 15.03.2016.
 */
public class FilmCollectionTest {

    @Test

    public void test() throws Exception{

        FilmCollection f = new FilmCollection();
        assertNull(f.getFilmFromCollection("test"));


    }

}
