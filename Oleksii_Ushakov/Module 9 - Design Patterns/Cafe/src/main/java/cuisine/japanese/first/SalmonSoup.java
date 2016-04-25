package cuisine.japanese.first;

import cuisine.FirstDishes;
import report.ReportMaker;

/**
 * @author Alexey Ushakov
 */
public class SalmonSoup implements FirstDishes {
    public SalmonSoup() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        reportMaker.addTask("Cooking salmon soup with sesame seeds");
    }

    @Override
    public String description() {
        return "Salmon soup with sesame seeds";
    }
}
