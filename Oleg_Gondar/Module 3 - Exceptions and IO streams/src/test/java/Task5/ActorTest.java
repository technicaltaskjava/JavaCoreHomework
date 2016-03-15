package Task5;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by Oleg on 12.03.2016.
 */
public class ActorTest {

    @Test

    public void test() throws Exception{

        Actor a = new Actor("First", "Second");
        assertEquals("First",a.getActorFirstName());
        assertEquals("Second", a.getActorLastName());

        
    }

}
