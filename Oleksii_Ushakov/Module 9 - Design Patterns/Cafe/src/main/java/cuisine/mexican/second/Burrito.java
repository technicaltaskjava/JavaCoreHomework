package cuisine.mexican.second;

import cuisine.SecondDishes;
import report.ReportMaker;

/**
 * @author Alexey Ushakov
 */
public class Burrito implements SecondDishes {
    public Burrito() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        reportMaker.addTask("Cooking burrito with chicken and peppers");
    }

    @Override
    public String description() {
        return "Burrito with chicken and peppers";
    }
}
