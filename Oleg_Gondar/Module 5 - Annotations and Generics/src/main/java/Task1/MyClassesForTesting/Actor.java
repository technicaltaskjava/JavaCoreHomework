package Task1.MyClassesForTesting;

import Task1.MyException;
import Task1.Annotations.Test;

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

    @Test(description = "Actor first name")
    public void setActorFirstName(String actorFirstName) {
        this.actorFirstName = actorFirstName;
    }

    @Test(description = "Actor last name")
    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }

    @Test(description = "work with exception", expected = "MyException")
    private void testException() throws MyException {

        throw new MyException("My exception is throwed!!!");

    }

    @Test(description = "Getter ignored", ignore = true)
    public String getActorFirstName() {
        return actorFirstName;
    }

    @Test(description = "Getter ignored", ignore = true)
    public String getActorLastName() {
        return actorLastName;
    }


}
