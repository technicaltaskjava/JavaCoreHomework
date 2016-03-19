package exceptions;


public class NoKeyFoundException extends Exception {
    private int detail;

    public NoKeyFoundException(int line) {
        detail = line;
    }

    public String toString(){
        return "No key found on line " + detail;
    }
}
