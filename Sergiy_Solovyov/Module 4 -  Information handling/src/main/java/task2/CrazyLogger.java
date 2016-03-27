package task2;

import java.io.*;
import java.util.*;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public class CrazyLogger implements Logging, Searchable {

    private StringBuilder sb = new StringBuilder(1024);
    private Calendar cal = Calendar.getInstance();
    private Formatter formatter = new Formatter();
    private OutputStream out;
    private Writer writer;


    public CrazyLogger(){

        formatter.format("%td-%<tm-%<tY : %<tH-%<tM ", cal);
        this.out = System.out;
    }
    public CrazyLogger(String fileName){
        try {
            this.out = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    @Override
    public void info(String massage) {
        Formatter f = new Formatter();
        sb.append(formatter);
        sb.append(f.format("[%s] - ", Level.INFO));
        sb.append(massage);
        sb.append("\n");
    }

    @Override
    public void conf(String massage) {
        Formatter f = new Formatter();
        sb.append(formatter);
        sb.append(f.format("[%s] - ", Level.CONFIG));
        sb.append(massage);
        sb.append("\n");
    }

    @Override
    public void warn(String massage) {
        Formatter f = new Formatter();
        sb.append(formatter);
        sb.append(f.format("[%s] - ", Level.WARNING));
        sb.append(massage);
        sb.append("\n");
    }

    @Override
    public void server(String massage) {
        Formatter f = new Formatter();
        sb.append(formatter);
        sb.append(f.format("[%s] - ", Level.SERVER));
        sb.append(massage);
        sb.append("\n");
    }

    @Override
    public void log(String massage, Throwable ex) {
        Formatter f = new Formatter();
        sb.append(formatter);
        sb.append(f.format("[%s] - ", Level.EXCEPTION));
        StackTraceElement[]trace = ex.getStackTrace();
        for (StackTraceElement s: trace){
            sb.append(s);
            sb.append("||||");
        }

        sb.append("\n");
    }

    @Override
    public void logsByLevel(Level level) {
        StringBuilder builder = new StringBuilder();
        String logs = sb.toString();
        String[]log = logs.split("\n");

        for (String s: log){
            if (s.contains(level.name())){
                builder.append(s);
                builder.append("\n");}
        }
        try {
            out.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logsByDate(int day, int month, int year) throws WrongDateException {

        StringBuilder builder = new StringBuilder();

        if ((day > 0 && day <= 31)&&(month > 0 && month <= 12) && year >= 2016 ){
            Formatter form = new Formatter();
            String date = form.format("%2d-%02d-%4d", day, month, year).toString();
            String logs = sb.toString();
            String[]log = logs.split("\n");
            for (String s: log){
                if (s.contains(date)){
                    builder.append(s);
                    builder.append("\n");}
            }
        }else
            throw new WrongDateException("Wrong date");

        try {
            out.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
