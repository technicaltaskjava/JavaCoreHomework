package Task5;

/**
 * Created by Oleg on 14.03.2016.
 */
public class Actor implements java.io.Serializable {

    private String actorFirstName;
    private String actorLastName;

    private Actor() {

    }

    public Actor(String actorFirstName, String actorLastName) {
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
    }

    public String getActorFirstName() {
        return actorFirstName;
    }

    public void setActorFirstName(String actorFirstName) {
        this.actorFirstName = actorFirstName;
    }

    public String getActorLastName() {
        return actorLastName;
    }

    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }
}
