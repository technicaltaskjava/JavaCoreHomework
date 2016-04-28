package cuisine.japanese.snack;

import cuisine.Snacks;
import report.ReportMaker;

/**
 * @author Alexey Ushakov
 */
public class TigerPrawns implements Snacks {
    public TigerPrawns() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        reportMaker.addTask("Cooking grilled tiger prawns in breadcrumbs with sauce");
    }

    @Override
    public String description() {

        return "Grilled tiger prawns in breadcrumbs with sauce";
    }
}
