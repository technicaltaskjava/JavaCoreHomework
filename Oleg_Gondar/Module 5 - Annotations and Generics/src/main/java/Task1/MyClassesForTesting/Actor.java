package Task1.MyClassesForTesting;

import Task1.MyException;
import Task1.Annotations.Test;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oleg on 14.03.2016.
 */
public class Actor {

    private String actorFirstName;
    private String actorLastName;

    public Actor() {

    }

    public Actor(String actorFirstName, String actorLastName) {
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
    }


    public void setActorFirstName(String actorFirstName) {
        this.actorFirstName = actorFirstName;
    }

    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }

    public void throwException() throws MyException {

        throw new MyException("!!!!!!!!!!!!!!!!");

    }


    @Test()
    public void testSetActorFirstName() {
        Actor a = new Actor();
        a.setActorFirstName("First");
        Assert.assertTrue(a.getActorFirstName().equals("First"));
        System.out.println("Actor first name test passed");

    }

    @Test()
    private void testSetActorLastName() {
        Actor a = new Actor();
        a.setActorLastName("Last");
        Assert.assertTrue(a.getActorLastName().equals("Last"));
        System.out.println("Actor last name test passed");

    }

    @Test(expected = MyException.class)
    public void testException() throws MyException {

        throwException();

    }

    @Test(ignore = true)
    public void ignoredTest() {
    }

    public String getActorFirstName() {
        return actorFirstName;
    }

    public String getActorLastName() {
        return actorLastName;
    }


}
