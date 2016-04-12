package entity;

/**
 *  @author Sergey Solovyov
 */
public class Actor {

    private String name;
    private int speechQuy;
    private int wordsQuy;

    public Actor(){
        this.speechQuy = 0;
    }
    public Actor(String name, int wordsQuy) {
        this.speechQuy = 1;
        this.name = name;
        this.wordsQuy = wordsQuy;
    }

    public int getSpeechQuy() {
        return speechQuy;
    }

    public int getWordsQuy() {
        return wordsQuy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpeechQuy(int speechQuy) {
        this.speechQuy = speechQuy;
    }

    public synchronized void setWordsQuy(int wordsQuy) {
        speechQuy++;
        this.wordsQuy = this.wordsQuy + wordsQuy;
    }
    private double getAverage(){
        if (wordsQuy == 0)
            return 0;
        else return (double) wordsQuy/speechQuy;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------------------------\n");
        builder.append(name);
        builder.append("\n");
        builder.append("replicas - ");
        builder.append(speechQuy);
        builder.append("\n");
        builder.append(String.format("average quantity words in replica - %.2f", getAverage()));
        builder.append("\n");
        builder.append("words in all play  - ");
        builder.append(wordsQuy);
        builder.append("\n");
        return builder.toString();
    }


}
