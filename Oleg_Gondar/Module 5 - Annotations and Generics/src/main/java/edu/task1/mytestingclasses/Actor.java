package edu.task1.mytestingclasses;

import edu.task1.annotations.Test;
import edu.task1.MyException;
import org.junit.Assert;


/**
 * Created by Oleg on 14.03.2016.
 */
public class Actor {

    private String actorFirstName;
    private String actorLastName;

    public Actor() {
        this.actorFirstName = null;
        this.actorLastName = null;
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


    @Test()
    public void testSetActorFirstName() {
        Actor a = new Actor();
        a.setActorFirstName("First");
        Assert.assertTrue("First".equals(a.getActorFirstName()));
        System.out.println("Actor first name test passed");

    }

    @Test()
    public void testSetActorLastName() {
        Actor a = new Actor();
        a.setActorLastName("Last");
        Assert.assertTrue("Last".equals(a.getActorLastName()));
        System.out.println("Actor last name test passed");

    }

    @Test(expected = MyException.class)
    public void testException() throws MyException {

        throw new MyException("!!!!!!!!!!!!!!!!");

    }

    @Test(ignore = true)
    public void ignoredTest() {

        System.out.println("Ignored method");

    }

    public String getActorFirstName() {
        return actorFirstName;
    }

    public String getActorLastName() {
        return actorLastName;
    }


}