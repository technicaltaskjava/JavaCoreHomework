package cuisine.mexican.first;

import cuisine.FirstDishes;
import report.ReportMaker;

/**
 * @author Alexey Ushakov
 */
public class ChillyBeanSoup implements FirstDishes {
    public ChillyBeanSoup() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        reportMaker.addTask("Cooking bean soup with chilly");
    }

    @Override
    public String description() {
        return "Bean soup with chilly";
    }
}
