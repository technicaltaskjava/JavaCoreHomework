package misc;

public class Words implements Comparable<Words> {
    private String word;
    private double ratio;

    public Words(){
        word = null;
        ratio = 0;
    }

    public Words(String word, double ratio){
        this.word = word;
        this.ratio = ratio;
    }

    public String getWord(){
        return word;
    }

    public int compareTo(Words next){
        if (Double.compare(this.ratio, next.ratio) > 0){
            return 1;
        } else if (Double.compare(this.ratio, next.ratio) == 0){
            return 0;
        } else {
            return -1;
        }
    }
}

