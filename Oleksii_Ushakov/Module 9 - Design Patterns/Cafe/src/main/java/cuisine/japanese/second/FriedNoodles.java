package cuisine.japanese.second;

import cuisine.SecondDishes;
import report.ReportMaker;

/**
 * @author Alexey Ushakov
 */
public class FriedNoodles implements SecondDishes {
    public FriedNoodles() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        reportMaker.addTask("Cooking fried noodles with shrimp, tofu and leek");
    }

    @Override
    public String description() {
        return "Fried noodles with shrimp, tofu and leek";
    }
}
