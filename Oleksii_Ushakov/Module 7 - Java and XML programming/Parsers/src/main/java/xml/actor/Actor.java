package xml.actor;

/**
 * @author Alexey Ushakov
 */
public class Actor {
    private String name;
    private int replicasCount;
    private int wordsCount;

    public Actor(String name) {
        this.name = name;
        this.wordsCount = 0;
        this.replicasCount = 0;
    }

    public String getName() {
        return name;
    }

    public void addWordsCount(int count) {
        wordsCount += count;
    }

    public void incReplicasCount() {
        replicasCount++;
    }

    public int getWordsAverageCount() {
        return wordsCount / replicasCount;
    }

    public int getReplicasCount() {
        return replicasCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    @Override
    public int hashCode() {
        return name != null
                ? name.hashCode()
                : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Actor actor = (Actor) o;

        return name != null
                ? name.equals(actor.name)
                : actor.name == null;

    }

    @Override
    public String toString() {
        return name + " " + hashCode();
    }
}
