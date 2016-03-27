package task2;



import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CrazyLogger {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
   private Calendar calendar = Calendar.getInstance();


   public String message(String message) {

       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(dateFormat.format(calendar.getTime())).append(" - ").append(message);
       return stringBuilder.toString();
   }
}
