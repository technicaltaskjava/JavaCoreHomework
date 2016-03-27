package Task2;

import java.util.Calendar;
import java.util.Formatter;

/**
 * Created by O.Gondar on 22.03.2016.
 */
public class LogsDateFormat {


    public static Formatter dateForLog() {

        Formatter formatter = new Formatter();
        Calendar calendar = Calendar.getInstance();

        return formatter.format("%td-%<tm-%<TY : %<tH-%<tM", calendar);

    }

}
