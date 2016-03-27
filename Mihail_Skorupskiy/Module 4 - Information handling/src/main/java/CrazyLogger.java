import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class CrazyLogger {
    private StringBuilder log = new StringBuilder("Log start.\n");

    public void addMessage(String message){
        if (message != null) {
            Calendar time = Calendar.getInstance();
            log.append(String.format("%1$td-%1$tm-%1$tY : %1$tH-%1$tM - ", time));
            log.append(message);
            log.append(";\n");
        }
    }

    public String search(CharSequence parameter){
        StringBuilder results = new StringBuilder();
        if (parameter != null){
            int currentLine = log.indexOf("\n");
            int lastLine = log.lastIndexOf("\n");
            while (currentLine != lastLine){
                int nextLine = log.indexOf("\n", currentLine+1);
                String temp = log.substring(currentLine+1, nextLine+1);
                if (temp.contains(parameter)){
                    results.append(temp);
                }
                currentLine = nextLine;
            }
        }
        if (results.toString().equals("")){
            results.append("No results.");
        }
        return results.toString();
    }

    public void searchToStream(CharSequence parameter, OutputStreamWriter writer) throws IOException{
        if (parameter != null){
            int currentLine = log.indexOf("\n");
            int lastLine = log.lastIndexOf("\n");
            while (currentLine != lastLine){
                int nextLine = log.indexOf("\n", currentLine+1);
                String temp = log.substring(currentLine+1, nextLine+1);
                if (temp.contains(parameter)){
                    try{
                        writer.write(temp);
                    } catch (IOException e){
                        throw e;
                    }

                }
                currentLine = nextLine;
            }
        }
    }

    public void dump(BufferedWriter writer) throws IOException{
        try {
            writer.write(log.toString());
            log.setLength(0);
        } catch (IOException e){
            throw e;
        }
    }

    public void show(){
        System.out.println(log.toString());
    }
}