package exceptions;

public class IncorrectFileFormatException extends Exception {
    private String detail;

    public IncorrectFileFormatException(String s) {
        detail = s;
    }

    public String toString(){
        return "Incorrect file format. " + detail;
    }
}
