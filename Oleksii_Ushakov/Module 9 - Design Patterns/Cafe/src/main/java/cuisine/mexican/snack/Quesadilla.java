package cuisine.mexican.snack;

import cuisine.Snacks;
import report.ReportMaker;

/**
 * @author Alexey Ushakov
 */
public class Quesadilla implements Snacks {
    public Quesadilla() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        reportMaker.addTask("Cooking quesadilla with chicken and cheddar");
    }

    @Override
    public String description() {
        return "Quesadilla with chicken and cheddar";
    }
}
