package elements;

public class Speech {
    private StringBuilder speaker;
    private StringBuilder line;

    public Speech(){
        speaker = new StringBuilder();
        line = new StringBuilder();
    }

    public void setSpeaker(String speaker){
        this.speaker.append(speaker);
    }

    public void setLine(String line){
        this.line.append(line);
    }

    public String getSpeaker(){
        return speaker.toString();
    }

    public String getLine(){
        return line.toString();
    }

    @Override
    public String toString(){
        return speaker + ": " + line.toString();
    }
}
